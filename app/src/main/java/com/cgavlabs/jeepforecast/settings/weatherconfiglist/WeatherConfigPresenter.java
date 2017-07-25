package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

import javax.inject.Inject;

public class WeatherConfigPresenter implements WeatherConfigContract.Presenter {
    private final WeatherConfigContract.Interactor interactor;

    @Inject
    public WeatherConfigPresenter(WeatherConfigContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public List<WeatherConfig> getWeatherConfigs() {
        return interactor.getWeatherConfigs();
    }

    @Override
    public void addWeatherConfig(WeatherConfig weatherConfig) {
        interactor.addWeatherconfig(weatherConfig);
    }
}
