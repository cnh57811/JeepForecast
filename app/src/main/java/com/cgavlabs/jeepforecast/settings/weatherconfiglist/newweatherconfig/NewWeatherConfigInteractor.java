package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import java.util.List;

import javax.inject.Inject;

public class NewWeatherConfigInteractor implements NewWeatherConfigContract.Interactor {
    private WeatherRepo weatherRepo;

    @Inject
    public NewWeatherConfigInteractor(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Override
    public List<WeatherConfig> getWeatherConfigs() {
        return weatherRepo.getWeatherConfigs();
    }
}
