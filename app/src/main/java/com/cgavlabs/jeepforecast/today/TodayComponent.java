package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.di.RealmModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { RealmModule.class }) interface TodayComponent {
  void inject(TodayFragment todayFragment);
}
