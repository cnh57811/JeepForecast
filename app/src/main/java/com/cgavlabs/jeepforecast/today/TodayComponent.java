package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.di.RealmModule;
import com.cgavlabs.jeepforecast.di.WeatherRepoModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = { TodayModule.class, WeatherRepoModule.class, RealmModule.class })
interface TodayComponent {
  void inject(TodayFragment todayFragment);
}
