package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.UserScope;
import dagger.Component;

@UserScope @Component(dependencies = { AppComponent.class }, modules = {
    WeatherConfigModule.class
}) public interface WeatherConfigComponent {
  void inject(WeatherConfigListActivity activity);
}
