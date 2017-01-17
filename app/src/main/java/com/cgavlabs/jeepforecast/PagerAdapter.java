package com.cgavlabs.jeepforecast;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.cgavlabs.jeepforecast.today.TodayFragment;

public class PagerAdapter extends FragmentPagerAdapter {

  public static final int NUM_FRAGMENTS = 3;

  public PagerAdapter(FragmentManager fm) {
    super(fm);
  }

  @Override public Fragment getItem(int position) {
    Fragment f;
    switch (position) {
      case 0:
        f = new TodayFragment();
        break;
      case 1:
        f = new TodayFragment();
        break;
      case 2:
        f = new TodayFragment();
        break;
      default:
        f = new TodayFragment();
    }
    return f;
  }

  @Override public CharSequence getPageTitle(int position) {
    String title = "";
    switch (position) {
      case 0:
        title = "TODAY";
        break;
      case 1:
        title = "TOMORROW";
        break;
      case 2:
        title = "WEEK";
        break;
    }
    return title;
  }

  @Override public int getCount() {
    return NUM_FRAGMENTS;
  }
}
