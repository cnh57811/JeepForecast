package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;

public interface WeatherConfigContract {
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
