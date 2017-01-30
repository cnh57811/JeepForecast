package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import dagger.Module;
import dagger.Provides;

@Module public class WeatherConfigModule {

  @Provides Contract.Config.Presenter providePresenter(Contract.Config.Interactor interactor) {
    return new WeatherConfigPresenter(interactor);
  }

  @Provides Contract.Config.Interactor provideInteractor(WeatherRepo weatherRepo) {
    return new WeatherConfigInteractor(weatherRepo);
  }
}
