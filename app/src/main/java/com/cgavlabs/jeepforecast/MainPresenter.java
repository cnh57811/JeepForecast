package com.cgavlabs.jeepforecast;

import android.util.Log;
import javax.inject.Inject;

public class MainPresenter implements Contract.Main.Presenter {

  private final Contract.Main.Interactor interactor;

  @Inject public MainPresenter(Contract.Main.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public void callWeather(Double latitude, Double longitude) {
    Log.d("INTERACTOR", "callWeather: interactor is " + interactor == null ? "null" : "NOT null");
    interactor.callWeather(latitude, longitude);
  }
}
