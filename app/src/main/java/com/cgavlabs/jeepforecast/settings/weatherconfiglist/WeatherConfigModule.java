package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import dagger.Module;
import dagger.Provides;

@Module public class WeatherConfigModule {

  @Provides WeatherConfigContract.Presenter providePresenter(WeatherConfigContract.Interactor interactor) {
    return new WeatherConfigPresenter(interactor);
  }

  @Provides WeatherConfigContract.Interactor provideInteractor(WeatherRepo weatherRepo) {
    return new WeatherConfigInteractor(weatherRepo);
  }
}
