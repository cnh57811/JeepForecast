package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;

public interface WeatherConfigContract {
  interface View {
    List<WeatherConfig> getWeatherConfigs();
  }

  interface Presenter {
    List<WeatherConfig> getWeatherConfigs();

    void addWeatherConfig(WeatherConfig weatherConfig);
  }

  interface Interactor {
    List<WeatherConfig> getWeatherConfigs();

    void addWeatherconfig(WeatherConfig weatherConfig);
  }
}
