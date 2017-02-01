package com.cgavlabs.jeepforecast.main;

import javax.inject.Inject;

public class MainPresenter implements MainContract.Presenter {

  private final MainContract.Interactor interactor;

  @Inject public MainPresenter(MainContract.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public void callWeather(Double latitude, Double longitude) {
    interactor.callWeather(latitude, longitude);
  }
}
