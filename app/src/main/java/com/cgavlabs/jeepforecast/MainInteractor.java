package com.cgavlabs.jeepforecast;

import android.util.Log;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import io.realm.Realm;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainInteractor implements Contract.Main.Interactor {

  static {
    System.loadLibrary("keys");
  }

  private final WeatherService weatherSvc;
  private final Realm realm;

  @Inject public MainInteractor(WeatherService weatherSvc, Realm realm) {
    this.weatherSvc = weatherSvc;
    this.realm = realm;
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

          @Override public void onNext(final Weather weather) {
            Log.d("MainInteractor", weather.toString());
            realm.executeTransactionAsync(new Realm.Transaction() {
              @Override public void execute(Realm realm) {
                realm.insertOrUpdate(weather);
              }
            }, new Realm.Transaction.OnSuccess() {
              @Override public void onSuccess() {
                Log.d("MainInteractor", "onSuccess: weather saved successfully");
              }
            }, new Realm.Transaction.OnError() {
              @Override public void onError(Throwable error) {
                Log.d("MainInteractor", "ruh roh", error);
              }
            });
          }
        });
  }
}
