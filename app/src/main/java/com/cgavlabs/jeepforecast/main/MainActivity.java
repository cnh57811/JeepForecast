package com.cgavlabs.jeepforecast.main;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.WeatherConfigListActivity;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject MainContract.Presenter presenter;
  @Inject MainPagerAdapter pagerAdapter;
  @Inject SharedPreferences sharedPrefs;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupViews();
    callWeather();
  }

  private void setupViews() {
    Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);
    ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
    TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
    viewPager.setAdapter(pagerAdapter);
    tabs.setupWithViewPager(viewPager);
  }

  private void callWeather() {
    presenter.callWeather(37.554239, -77.658531);
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_weather_config) {
      startActivity(new Intent(this, WeatherConfigListActivity.class));
    }
    return true;
  }

  @Override public void inject() {
    DaggerMainComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .mainModule(new MainModule(getSupportFragmentManager()))
        .build()
        .inject(this);
  }
}
