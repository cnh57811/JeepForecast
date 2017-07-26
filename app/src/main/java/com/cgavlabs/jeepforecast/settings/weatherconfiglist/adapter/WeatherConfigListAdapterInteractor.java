package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import java.util.List;

import javax.inject.Inject;

class WeatherConfigListAdapterInteractor implements WeatherConfigListAdapterContract.Interactor {

    private final WeatherRepo weatherRepo;

    @Inject
    WeatherConfigListAdapterInteractor(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Override
    public List<WeatherConfig> getAllWeatherConfigs() {
        return weatherRepo.getAllWeatherConfigs();
    }
}
