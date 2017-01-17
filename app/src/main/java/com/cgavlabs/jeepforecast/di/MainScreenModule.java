package com.cgavlabs.jeepforecast.di;

import android.support.v4.app.FragmentManager;
import com.cgavlabs.jeepforecast.PagerAdapter;
import dagger.Module;
import dagger.Provides;

@Module public class MainScreenModule {

  private final FragmentManager fm;

  public MainScreenModule(FragmentManager fm) {
    this.fm = fm;
  }

  @Provides public PagerAdapter providesPagerAdapter() {
    return new PagerAdapter(fm);
  }
}
