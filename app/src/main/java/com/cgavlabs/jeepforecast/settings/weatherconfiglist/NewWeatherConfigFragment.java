package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.utils.RxHelper;
import java.util.concurrent.TimeUnit;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.cgavlabs.jeepforecast.Constants.SELECT_PICTURE;

public class NewWeatherConfigFragment extends DialogFragment {
  private static final String REGEX_IS_NUMBER = "^[0-9]*$";

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
    jeepImg.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
      }
    });
    EditText nameET = (EditText) dialogView.findViewById(R.id.config_name);
    EditText lowTempET = (EditText) dialogView.findViewById(R.id.config_temp_low);
    EditText highTempET = (EditText) dialogView.findViewById(R.id.config_temp_high);
    EditText precipET = (EditText) dialogView.findViewById(R.id.config_precip_threshold);

    Observable<Boolean> nameObservable = RxHelper.getTextWatcherObservable(nameET)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> true);

    Observable<Boolean> lowTempObservable = RxHelper.getTextWatcherObservable(lowTempET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> s.trim().matches(REGEX_IS_NUMBER));

    Observable<Boolean> highTempObservable = RxHelper.getTextWatcherObservable(highTempET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> s.trim().matches(REGEX_IS_NUMBER));

    Observable<Boolean> precipThreshObservable = RxHelper.getTextWatcherObservable(precipET)
        .debounce(500, TimeUnit.MILLISECONDS)
        .observeOn(AndroidSchedulers.mainThread())
        .map(s -> s.trim().matches(REGEX_IS_NUMBER));

    AlertDialog.Builder builder = new AlertDialog.Builder(getContext()).setView(dialogView)
        .setPositiveButton("ADD", (dialogInterface, i) -> {
          String name = nameET.getText().toString();
          Integer lowTemp = Integer.valueOf(lowTempET.getText().toString());
          Integer highTemp = Integer.valueOf(highTempET.getText().toString());
          Integer precipThresh = Integer.valueOf(precipET.getText().toString());
          String imgUri = (String) jeepImg.getTag();
          WeatherConfig wc = new WeatherConfig(name, highTemp, lowTemp, precipThresh, imgUri);
          listener.onAddNewWeatherConfig(wc);
        })
        .setNegativeButton("CANCEL", (dialog, i) -> dialog.cancel());
    AlertDialog dialog = builder.create();
    dialog.setOnShowListener(
        dialog1 -> ((AlertDialog) dialog1).getButton(AlertDialog.BUTTON_POSITIVE)
            .setEnabled(false));

    Observable.combineLatest(nameObservable, lowTempObservable, highTempObservable,
        precipThreshObservable, (validName, validLowTemp, validHighTemp, validPrecipThresh) ->
            validName
                && validLowTemp
                && validHighTemp
                && validPrecipThresh).subscribe(enabled -> {
      dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enabled);
    });
    return dialog;
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      if (requestCode == SELECT_PICTURE) {
        Timber.d("image selected " + data.getData());
        Glide.with(this).load(data.getData()).into(jeepImg);
        jeepImg.setTag(data.getData().toString());
      }
    }
  }

  public interface NewWeatherConfigDialogListener {
    void onAddNewWeatherConfig(WeatherConfig wc);
  }
}
