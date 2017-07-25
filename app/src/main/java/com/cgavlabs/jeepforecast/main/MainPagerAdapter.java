package com.cgavlabs.jeepforecast.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.cgavlabs.jeepforecast.today.TodayFragment;
import com.cgavlabs.jeepforecast.tomorrow.TomorrowFragment;

import javax.inject.Inject;

public class MainPagerAdapter extends FragmentPagerAdapter {

    private static final int NUM_FRAGMENTS = 3;

    @Inject
    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment f;
        switch (position) {
            case 0:
                f = TodayFragment.newInstance();
                break;
            case 1:
                f = new TomorrowFragment();
                break;
            case 2:
                f = new TomorrowFragment();
                break;
            default:
                f = new TomorrowFragment();
        }
        return f;
    }

    @Override
    public CharSequence getPageTitle(int position) {
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

    @Override
    public int getCount() {
        return NUM_FRAGMENTS;
    }
}
