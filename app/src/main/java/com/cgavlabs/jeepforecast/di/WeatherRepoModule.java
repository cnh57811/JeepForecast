package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.repos.WeatherRepoImpl;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module public class WeatherRepoModule {

  @Provides public WeatherRepo provideWeatherRepo(Realm realm) {
    return new WeatherRepoImpl(realm);
  }
}
