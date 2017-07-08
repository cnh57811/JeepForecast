package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.UserScope;
import dagger.Component;

@UserScope
@Component(dependencies = { AppComponent.class }, modules = { NewWeatherConfigModule.class })
public interface NewWeatherConfigComponent {
  void inject(NewWeatherConfigFragment fragment);
}
