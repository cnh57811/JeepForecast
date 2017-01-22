package com.cgavlabs.jeepforecast;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.cgavlabs.jeepforecast.di.DaggerMainScreenComponent;
import com.cgavlabs.jeepforecast.di.MainScreenModule;
import javax.inject.Inject;

public class MainActivity extends BaseActivity {

  @Inject PagerAdapter pagerAdapter;
  @Inject Contract.Main.Presenter presenter;
  private Toolbar toolbar;
  private ViewPager viewPager;
  private TabLayout tabs;

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    setupViews();
    setSupportActionBar(toolbar);
    callWeather();
  }

  private void callWeather() {
    presenter.callWeather(37.554239, -77.658531);
  }

  private void setupViews() {
    toolbar = (Toolbar) findViewById(R.id.toolbar);
    viewPager = (ViewPager) findViewById(R.id.viewPager);
    tabs = (TabLayout) findViewById(R.id.tabs);
    viewPager.setAdapter(pagerAdapter);
    tabs.setupWithViewPager(viewPager);
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
    DaggerMainScreenComponent.builder()
        .mainScreenModule(new MainScreenModule(getSupportFragmentManager()))
        .build()
        .inject(this);
  }
}
