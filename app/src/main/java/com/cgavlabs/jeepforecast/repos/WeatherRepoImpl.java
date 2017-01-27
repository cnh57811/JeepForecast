package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.models.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import io.realm.Realm;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import timber.log.Timber;

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
        Timber.d("Weather successfully persisted to db");
        EventBus.getDefault().post(new DataSavedEvent());
      }
    }, new Realm.Transaction.OnError() {
      @Override public void onError(Throwable error) {
        Timber.e(error);
      }
    });
  }

  @Override public DailyData getTodaysWeather() {
    Long time = (Long) realm.where(DailyData.class).max("time");
    return realm.where(DailyData.class).equalTo("time", time).findFirst();
  }

  @Override public Currently getCurrentWeather() {
    return realm.where(Currently.class).findFirst();
  }
}
