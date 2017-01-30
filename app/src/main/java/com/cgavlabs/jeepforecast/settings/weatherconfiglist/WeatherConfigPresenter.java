package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;
import javax.inject.Inject;

public class WeatherConfigPresenter implements Contract.Config.Presenter {
  private final Contract.Config.Interactor interactor;

  @Inject
  public WeatherConfigPresenter(Contract.Config.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public List<WeatherConfig> getWeatherConfigs() {
    return interactor.getWeatherConfigs();
  }
}
