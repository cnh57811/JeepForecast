package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.main.UserScope;
import dagger.Component;

@UserScope @Component(dependencies = { AppComponent.class }, modules = {
    TodayModule.class
}) interface TodayComponent {
  void inject(TodayFragment todayFragment);
}
