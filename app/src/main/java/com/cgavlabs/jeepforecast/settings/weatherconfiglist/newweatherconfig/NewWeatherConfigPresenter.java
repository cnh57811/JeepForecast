package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;
import javax.inject.Inject;

public class NewWeatherConfigPresenter implements NewWeatherConfigContract.Presenter {

  private NewWeatherConfigContract.Interactor interactor;

  @Inject public NewWeatherConfigPresenter(NewWeatherConfigContract.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public List<WeatherConfig> getWeatherConfigs() {
    return interactor.getWeatherConfigs();
  }
}
