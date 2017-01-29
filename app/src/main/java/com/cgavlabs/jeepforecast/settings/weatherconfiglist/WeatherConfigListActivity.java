package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.settings.newweatherconfig.NewWeatherConfigActivity;
import java.util.ArrayList;
import java.util.List;

public class WeatherConfigListActivity extends AppCompatActivity {

  private RecyclerView recyclerView;
  private FloatingActionButton fab;

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
    recyclerView.setAdapter(new WeatherConfigListAdapter(setupMockWeatherConfigs()));
    fab = (FloatingActionButton) findViewById(R.id.fab_add_weather_config);
    fab.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View view) {
        startActivity(new Intent(WeatherConfigListActivity.this, NewWeatherConfigActivity.class));
      }
    });
  }

  private List<WeatherConfig> setupMockWeatherConfigs() {
    List<WeatherConfig> weatherConfigs = new ArrayList<>();
    weatherConfigs.add(new WeatherConfig());
    weatherConfigs.add(new WeatherConfig());
    weatherConfigs.add(new WeatherConfig());
    weatherConfigs.add(new WeatherConfig());
    weatherConfigs.add(new WeatherConfig());
    return weatherConfigs;
  }
}
