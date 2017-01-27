package com.cgavlabs.jeepforecast.main;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.R;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject MainPagerAdapter pagerAdapter;
  @Inject Contract.Main.Presenter presenter;

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
    return id == R.id.action_settings || super.onOptionsItemSelected(item);
  }

  @Override public void inject() {
    DaggerMainComponent.builder()
        .mainModule(new MainModule(getSupportFragmentManager()))
        .build()
        .inject(this);
  }
}
