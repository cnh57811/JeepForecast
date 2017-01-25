package com.cgavlabs.jeepforecast.repos;

import android.util.Log;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import io.realm.Realm;
import javax.inject.Inject;

public class WeatherRepoImpl implements WeatherRepo {
  private final Realm realm;

  @Inject public WeatherRepoImpl(Realm realm) {
    this.realm = realm;
  }

  @Override public void insertOrUpdate(final Weather weather) {
    realm.executeTransactionAsync(new Realm.Transaction() {
      @Override public void execute(Realm realm) {
        realm.insertOrUpdate(weather);
      }
    }, new Realm.Transaction.OnSuccess() {
      @Override public void onSuccess() {
        Log.d("WeatherRepoImpl", "onSuccess: weather saved successfully");
      }
    }, new Realm.Transaction.OnError() {
      @Override public void onError(Throwable error) {
        Log.d("WeatherRepoImpl", "ruh roh", error);
      }
    });
  }

  @Override public Data getTodaysWeather() {
    return realm.where(Data.class).findFirst();
  }

  @Override public Currently getCurrentWeather() {
    return realm.where(Currently.class).findFirst();
  }
}