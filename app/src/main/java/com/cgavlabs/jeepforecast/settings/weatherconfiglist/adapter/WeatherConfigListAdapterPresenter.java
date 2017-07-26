package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

import javax.inject.Inject;

class WeatherConfigListAdapterPresenter implements WeatherConfigListAdapterContract.Presenter {

    private WeatherConfigListAdapterContract.Interactor interactor;

    @Inject
    WeatherConfigListAdapterPresenter(
            WeatherConfigListAdapterContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public List<WeatherConfig> getAllWeatherConfigs() {
        return interactor.getAllWeatherConfigs();
    }

}
