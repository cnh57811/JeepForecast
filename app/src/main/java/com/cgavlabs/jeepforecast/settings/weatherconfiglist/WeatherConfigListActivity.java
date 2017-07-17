package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.Constants;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter.WeatherConfigListAdapter;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig.NewWeatherConfigFragment;
import javax.inject.Inject;

public class WeatherConfigListActivity extends BaseActivity
    implements NewWeatherConfigFragment.NewWeatherConfigDialogListener, View.OnLongClickListener {

  public static final String NEW_WEATHER_CONFIG = "NewWeatherConfig";
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
    FragmentManager fm = getSupportFragmentManager();
    NewWeatherConfigFragment dialog = new NewWeatherConfigFragment();
    dialog.show(fm, NEW_WEATHER_CONFIG);
  }

  @Override public boolean onLongClick(View view) {
    TextView highTempTv = ButterKnife.findById(view, R.id.weather_config_list_item_high_temp);
    TextView lowTempTv = ButterKnife.findById(view, R.id.weather_config_list_item_low_temp);
    TextView highPrecipTv = ButterKnife.findById(view, R.id.weather_config_list_item_high_precip);
    TextView lowPrecipTv = ButterKnife.findById(view, R.id.weather_config_list_item_low_precip);
    //String tag = (String)view.findViewById(R.id.weather_config_list_item_image).getTag();
    String highTemp = highTempTv.getText().toString();
    String lowTemp = lowTempTv.getText().toString();
    String highPrecip = highPrecipTv.getText().toString();
    String lowPrecip = lowPrecipTv.getText().toString();
    FragmentManager fm = getSupportFragmentManager();
    NewWeatherConfigFragment dialog = new NewWeatherConfigFragment();
    Bundle b = new Bundle();
    b.putString(Constants.HIGH_TEMP, highTemp);
    b.putString(Constants.LOW_TEMP, lowTemp);
    b.putString(Constants.HIGH_PRECIP, highPrecip);
    b.putString(Constants.LOW_PRECIP, lowPrecip);
    dialog.setArguments(b);
    dialog.show(fm, NEW_WEATHER_CONFIG);
    return true;
  }

  @Override public void onAddNewWeatherConfig(WeatherConfig wc) {
    presenter.addWeatherConfig(wc);
    adapter.refreshWeatherConfigList();
  }

  @Override public void inject() {
    DaggerWeatherConfigComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .weatherConfigModule(new WeatherConfigModule(this))
        .build()
        .inject(this);
  }
}
