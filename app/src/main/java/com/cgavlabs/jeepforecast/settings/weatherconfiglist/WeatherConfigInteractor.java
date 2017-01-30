package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import java.util.List;
import javax.inject.Inject;

public class WeatherConfigInteractor implements Contract.Config.Interactor {
  private final WeatherRepo weatherRepo;

  @Inject public WeatherConfigInteractor(WeatherRepo weatherRepo) {
    this.weatherRepo = weatherRepo;
  }

  @Override public List<WeatherConfig> getWeatherConfigs() {
    return weatherRepo.getWeatherConfigs();
  }
}
