package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseDialogFragment;
import com.cgavlabs.jeepforecast.Constants;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.utils.RxHelper;
import com.cgavlabs.jeepforecast.utils.Utils;
import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.cgavlabs.jeepforecast.Constants.SELECT_PICTURE;

public class NewWeatherConfigFragment extends BaseDialogFragment {
  private static final String REGEX_IS_NUMBER = "^[0-9]*$";
  private static final String LOW_IS_ABOVE_HIGH = "Low temp must be lower than the high temp";
  private static final String TEMPS_OVERLAP = "This temp range overlaps another weather config";
  @Inject NewWeatherConfigContract.Presenter presenter;
  private NewWeatherConfigDialogListener listener;
  private ImageView jeepImg;

  @Override public void onAttach(Context context) {
    super.onAttach(context);
    listener = ((NewWeatherConfigDialogListener) context);
  }

  @NonNull @Override public Dialog onCreateDialog(Bundle savedInstanceState) {
    View dialogView =
        LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_weather_config, null);
    jeepImg = (ImageView) dialogView.findViewById(R.id.config_add_image);
    jeepImg.setOnClickListener(view -> {
      Intent intent = new Intent();
      intent.setType("image/*");
      intent.setAction(Intent.ACTION_GET_CONTENT);
      startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
    });
    EditText lowTempET = (EditText) dialogView.findViewById(R.id.config_temp_low);
    EditText highTempET = (EditText) dialogView.findViewById(R.id.config_temp_high);
    EditText lowPrecipET = (EditText) dialogView.findViewById(R.id.config_precip_low);
    EditText highPrecipET = (EditText) dialogView.findViewById(R.id.config_precip_high);

    Bundle paramsToEdit = getArguments();
    String highTempString = null;
    String lowTempString = null;
    String highPrecipString = null;
    String lowPrecipString = null;
    String positiveButton = null;
    if (paramsToEdit != null) {
      positiveButton = "SAVE";
      highTempString = paramsToEdit.getString(Constants.HIGH_TEMP);
      lowTempString = paramsToEdit.getString(Constants.LOW_TEMP);
      highPrecipString = paramsToEdit.getString(Constants.HIGH_PRECIP);
      lowPrecipString = paramsToEdit.getString(Constants.LOW_PRECIP);
    } else {
      positiveButton = "ADD";
    }
    highTempET.setText(highTempString);
    lowTempET.setText(lowTempString);
    highPrecipET.setText(highPrecipString);
    lowPrecipET.setText(lowPrecipString);

    List<WeatherConfig> weatherConfigs = presenter.getWeatherConfigs();

    Observable<Integer> lowTempObservable = getTextWatcherObservable(lowTempET, Integer.MAX_VALUE);
    Observable<Integer> highTempObservable =
        getTextWatcherObservable(highTempET, Integer.MIN_VALUE);

    Observable<Boolean> lowIsLowerThanHighObservable =
        Observable.combineLatest(lowTempObservable, highTempObservable,
            (lowTemp, highTemp) -> lowTemp < highTemp);

    Observable<Boolean> tempsOverlapObservable =
        Observable.combineLatest(lowTempObservable, highTempObservable, (lowTemp, highTemp) -> {
          for (WeatherConfig wc : weatherConfigs) {
            boolean lowTempOverlaps = lowTemp > wc.getLowTemp() && lowTemp < wc.getHighTemp();
            boolean highTempOverlaps = highTemp > wc.getLowTemp() && highTemp < wc.getHighTemp();
            if (lowTempOverlaps || highTempOverlaps) {
              return true;
            }
          }
          return false;
        });

    Observable<Boolean> lowPrecipObservable = RxHelper.getTextWatcherObservable(lowPrecipET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> s.trim().matches(REGEX_IS_NUMBER));

    Observable<Boolean> highPrecipObservable = RxHelper.getTextWatcherObservable(highPrecipET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> true);

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(dialogView)
        .setPositiveButton(positiveButton, (dialogInterface, i) -> {
          Integer lowTemp = Integer.valueOf(lowTempET.getText().toString());
          Integer highTemp = Integer.valueOf(highTempET.getText().toString());
          Integer lowPrecip = Integer.valueOf(lowPrecipET.getText().toString());
          Integer highPrecip = Integer.valueOf(highPrecipET.getText().toString());
          String imgUri = (String) jeepImg.getTag();
          WeatherConfig wc = new WeatherConfig(lowTemp, highTemp, lowPrecip, highPrecip, imgUri);
          listener.onAddNewWeatherConfig(wc);
        })
        .setNegativeButton("CANCEL", (dialog, i) -> dialog.cancel());
    AlertDialog dialog = builder.create();
    dialog.setOnShowListener(
        dialog1 -> ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE)
            .setEnabled(false));

    Observable.combineLatest(lowIsLowerThanHighObservable, tempsOverlapObservable,
        (lowIsLowerThanHigh, tempsOverlap) -> {
          String error = null;
          if (!lowIsLowerThanHigh) {
            error = LOW_IS_ABOVE_HIGH;
          } else if (tempsOverlap) {
            error = TEMPS_OVERLAP;
          }
          return error;
        }).subscribe(error -> {
      dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(error == null);
      lowTempET.setError(error);
      highTempET.setError(error);
    }, throwable -> Timber.e(throwable, "Uh oh"));

    return dialog;
  }

  @NonNull
  private Observable<Integer> getTextWatcherObservable(EditText lowTempET, Integer dfault) {
    return RxHelper.getTextWatcherObservable(lowTempET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> Utils.getIntegerOrDefault(s, dfault));
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (resultCode == RESULT_OK) {
      if (requestCode == SELECT_PICTURE) {
        Timber.d("image selected " + data.getData());
        jeepImg.setBackground(null);
        Glide.with(this).load(data.getData()).into(jeepImg);
        jeepImg.setTag(data.getData().toString());
      }
    }
  }

  @Override protected void inject() {
    DaggerNewWeatherConfigComponent.builder()
        .appComponent(((App) getActivity().getApplication()).getAppComponent())
        .build()
        .inject(this);
  }

  public interface NewWeatherConfigDialogListener {
    void onAddNewWeatherConfig(WeatherConfig wc);
  }
}
