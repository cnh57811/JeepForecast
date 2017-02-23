package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter.WeatherConfigListAdapter;
import com.cgavlabs.jeepforecast.utils.RxHelper;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

public class WeatherConfigListActivity extends BaseActivity {

  private static final String REGEX_IS_NUMBER = "^[0-9]*$";
  @Inject WeatherConfigContract.Presenter presenter;
  @Inject WeatherConfigListAdapter adapter;
  @BindView(R.id.weather_config_toolbar) Toolbar toolbar;
  @BindView(R.id.weather_config_list) RecyclerView recyclerView;
  @BindView(R.id.fab_add_weather_config) FloatingActionButton fab;
  private Animator fabAnim;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_config_list);
    ButterKnife.bind(this);
    setupViews();
  }

  private void setupViews() {
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("Weather Configurations");
    }
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter.setWeatherConfigs(presenter.getWeatherConfigs());
    recyclerView.setAdapter(adapter);
    fabAnim = AnimatorInflater.loadAnimator(this, R.animator.rotate_fwd);
    fabAnim.setTarget(fab);
  }

  @OnClick(R.id.fab_add_weather_config) public void onClick() {
    fabAnim.start();

    View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_weather_config, null);
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

    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setView(dialogView);
    builder.setPositiveButton("ADD", (dialogInterface, i) -> {
      String name = nameET.getText().toString();
      Integer lowTemp = Integer.valueOf(lowTempET.getText().toString());
      Integer highTemp = Integer.valueOf(highTempET.getText().toString());
      Integer precipThresh = Integer.valueOf(precipET.getText().toString());
      WeatherConfig wc = new WeatherConfig(name, highTemp, lowTemp, precipThresh);
      presenter.addWeatherConfig(wc);
      adapter.refreshWeatherConfigList();
    });
    builder.setNegativeButton("CANCEL", (dialog, i) -> dialog.cancel());
    AlertDialog dialog = builder.create();
    dialog.setOnShowListener(new DialogInterface.OnShowListener() {
      @Override public void onShow(DialogInterface dialog) {
        ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
      }
    });
    dialog.show();

    Observable.combineLatest(nameObservable, lowTempObservable, highTempObservable,
        precipThreshObservable, (validName, validLowTemp, validHighTemp, validPrecipThresh) ->
            validName
                && validLowTemp
                && validHighTemp
                && validPrecipThresh).subscribe(enabled -> {
      dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(enabled);
    });
  }

  @Override public void inject() {
    DaggerWeatherConfigComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .build()
        .inject(this);
  }
}
