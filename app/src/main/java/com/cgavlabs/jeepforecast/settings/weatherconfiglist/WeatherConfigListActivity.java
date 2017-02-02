package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.services.BitmapService;
import javax.inject.Inject;

public class WeatherConfigListActivity extends BaseActivity {

  @Inject WeatherConfigContract.Presenter presenter;
  @Inject BitmapService bitmapSvc;
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
    WeatherConfigListAdapter adapter =
        new WeatherConfigListAdapter(bitmapSvc, presenter.getWeatherConfigs());
    recyclerView.setAdapter(adapter);
    fabAnim = AnimatorInflater.loadAnimator(this, R.animator.rotate_fwd);
    fabAnim.setTarget(fab);
  }

  @OnClick(R.id.fab_add_weather_config) public void onClick() {
    fabAnim.start();
  }

  @Override public void inject() {
    DaggerWeatherConfigComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .build()
        .inject(this);
  }
}
