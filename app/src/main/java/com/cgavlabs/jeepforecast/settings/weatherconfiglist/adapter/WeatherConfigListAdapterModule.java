package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherConfigListAdapterModule {

    @Provides
    WeatherConfigListAdapterContract.Presenter providePresenter(
            WeatherConfigListAdapterContract.Interactor interactor) {
        return new WeatherConfigListAdapterPresenter(interactor);
    }

    @Provides
    WeatherConfigListAdapterContract.Interactor provideInteractor(WeatherRepo weatherRepo) {
        return new WeatherConfigListAdapterInteractor(weatherRepo);
    }
}
