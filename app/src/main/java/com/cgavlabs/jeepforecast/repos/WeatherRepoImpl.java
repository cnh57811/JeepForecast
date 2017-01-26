package com.cgavlabs.jeepforecast.repos;

import android.util.Log;
import com.cgavlabs.jeepforecast.models.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import io.realm.Realm;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;

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
        EventBus.getDefault().post(new DataSavedEvent());
      }
    }, new Realm.Transaction.OnError() {
      @Override public void onError(Throwable error) {
        Log.d("WeatherRepoImpl", "ruh roh", error);
      }
    });
  }

  @Override public Data getTodaysWeather() {
    return realm.where(Data.class).equalTo("time", Double.valueOf(1485147600)).findFirst();
  }

  @Override public Currently getCurrentWeather() {
    Double maxTime = (Double) realm.where(Currently.class).max("time");
    return realm.where(Currently.class).equalTo("time", maxTime).findFirst();
  }
}
