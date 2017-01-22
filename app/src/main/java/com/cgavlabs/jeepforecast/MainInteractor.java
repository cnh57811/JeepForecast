package com.cgavlabs.jeepforecast;

import android.util.Log;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainInteractor implements Contract.Main.Interactor {

  static {
    System.loadLibrary("keys");
  }

  private final WeatherService weatherSvc;

  @Inject public MainInteractor(WeatherService weatherSvc) {
    this.weatherSvc = weatherSvc;
  }

  private native String getDarkSkyKey();

  @Override public void callWeather(Double latitude, Double longitude) {
    weatherSvc.getWeather(getDarkSkyKey(), latitude, longitude)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Weather>() {
          @Override public void onCompleted() {
            Log.d("MainInteractor", "COMPLETED");
          }

          @Override public void onError(Throwable e) {
            Log.e("MainInteractor", "onError: ", e);
          }

          @Override public void onNext(Weather weather) {
            Log.d("MainInteractor", weather.toString());
          }
        });
  }
}
