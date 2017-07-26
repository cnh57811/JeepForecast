package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

public interface WeatherConfigListAdapterContract {
    interface Presenter {
        List<WeatherConfig> getAllWeatherConfigs();
    }

    interface Interactor {
        List<WeatherConfig> getAllWeatherConfigs();
    }
}
