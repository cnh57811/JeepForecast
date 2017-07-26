package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.di.WeatherConfigScope;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import dagger.Module;
import dagger.Provides;

@Module
class NewWeatherConfigModule {

    @WeatherConfigScope
    @Provides
    public NewWeatherConfigContract.Presenter providePresenter(
            NewWeatherConfigContract.Interactor interactor) {
        return new NewWeatherConfigPresenter(interactor);
    }

    @WeatherConfigScope
    @Provides
    public NewWeatherConfigContract.Interactor provideInteractor(WeatherRepo weatherRepo) {
        return new NewWeatherConfigInteractor(weatherRepo);
    }
}
