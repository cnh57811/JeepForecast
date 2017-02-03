package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import com.cgavlabs.jeepforecast.services.BitmapService;
import dagger.Module;
import dagger.Provides;

@Module public class WeatherConfigListAdapterModule {

  @Provides WeatherConfigListAdapterContract.Presenter providePresenter(
      WeatherConfigListAdapterContract.Interactor interactor) {
    return new WeatherConfigListAdapterPresenter(interactor);
  }

  @Provides WeatherConfigListAdapterContract.Interactor provideInteractor(
      BitmapService bitmapService) {
    return new WeatherConfigListAdapterInteractor(bitmapService);
  }
}
