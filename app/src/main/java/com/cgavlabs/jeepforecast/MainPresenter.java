package com.cgavlabs.jeepforecast;

import javax.inject.Inject;

public class MainPresenter implements Contract.Main.Presenter {

  private final Contract.Main.Interactor interactor;

  @Inject public MainPresenter(Contract.Main.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public void callWeather(Double latitude, Double longitude) {
    interactor.callWeather(latitude, longitude);
  }
}
