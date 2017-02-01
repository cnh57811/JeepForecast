package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.services.BitmapService;
import com.cgavlabs.jeepforecast.settings.newweatherconfig.NewWeatherConfigActivity;
import javax.inject.Inject;

public class WeatherConfigListActivity extends BaseActivity {

  @Inject Contract.Config.Presenter presenter;
  @Inject BitmapService bitmapSvc;
  private RecyclerView recyclerView;
  private FloatingActionButton fab;
  private WeatherConfigListAdapter adapter;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_weather_config_list);
    setupViews();
  }

  private void setupViews() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.weather_config_toolbar);
    setSupportActionBar(toolbar);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
      getSupportActionBar().setTitle("Weather Configurations");
    }
    recyclerView = (RecyclerView) findViewById(R.id.weather_config_list);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    adapter = new WeatherConfigListAdapter(bitmapSvc, presenter.getWeatherConfigs());
    recyclerView.setAdapter(adapter);
    fab = (FloatingActionButton) findViewById(R.id.fab_add_weather_config);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(WeatherConfigListActivity.this, NewWeatherConfigActivity.class));
      }
    });
  }

  @Override public void inject() {
    DaggerWeatherConfigComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .build()
        .inject(this);
  }
}
