package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.repos.WeatherRepoImpl;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import javax.inject.Singleton;

@Module public class WeatherRepoModule {

  @Provides @Singleton public WeatherRepo provideWeatherRepo(Realm realm) {
    return new WeatherRepoImpl(realm);
  }
}
