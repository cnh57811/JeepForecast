package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter.WeatherConfigListAdapter;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter.WeatherConfigListAdapterContract;
import dagger.Module;
import dagger.Provides;

@Module public class WeatherConfigModule {

  @Provides WeatherConfigListAdapter provideAdapter(
      WeatherConfigListAdapterContract.Presenter presenter) {
    return new WeatherConfigListAdapter(presenter);
  }

  @Provides WeatherConfigContract.Presenter providePresenter(
      WeatherConfigContract.Interactor interactor) {
    return new WeatherConfigPresenter(interactor);
  }

  @Provides WeatherConfigContract.Interactor provideInteractor(WeatherRepo weatherRepo) {
    return new WeatherConfigInteractor(weatherRepo);
  }
}
