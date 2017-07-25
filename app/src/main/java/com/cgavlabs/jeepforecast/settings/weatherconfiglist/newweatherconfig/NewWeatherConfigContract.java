package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

public interface NewWeatherConfigContract {
    interface View {
        List<WeatherConfig> getWeatherConfigs();
    }

    interface Presenter {
        List<WeatherConfig> getWeatherConfigs();
    }

    interface Interactor {
        List<WeatherConfig> getWeatherConfigs();
    }
}
