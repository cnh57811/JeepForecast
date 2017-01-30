package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.di.RealmModule;
import com.cgavlabs.jeepforecast.di.WeatherRepoModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { WeatherConfigModule.class, WeatherRepoModule.class, RealmModule.class })
public interface WeatherConfigComponent {
  void inject(WeatherConfigListActivity activity);
}
