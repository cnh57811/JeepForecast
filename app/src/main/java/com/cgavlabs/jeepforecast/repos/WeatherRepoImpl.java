package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.events.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import io.realm.Realm;
import java.util.List;
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
        Timber.d("new weather successfully persisted to db");
        EventBus.getDefault().post(new DataSavedEvent());
      }
    }, new Realm.Transaction.OnError() {
      @Override public void onError(Throwable error) {
        Timber.e(error);
      }
    });
  }

  @Override public DailyData getLatestDailyData() {
    int latestTime = realm.where(DailyData.class).max("time").intValue();
    return realm.where(DailyData.class).equalTo("time", latestTime).findFirst();
  }

  @Override public Weather getLatestWeather(Double latitude, Double longitude) {
    int latestTime = realm.where(Currently.class).max("time").intValue();
    Timber.d("getLatestWeather: Lat:%s Lng:%s", latitude, longitude);
    return realm.where(Weather.class)
        .equalTo("latitude", latitude)
        .equalTo("longitude", longitude)
        .equalTo("currently.time", latestTime)
        .findFirst();
  }

  @Override public Currently getCurrentWeather() {
    return realm.where(Currently.class).findFirst();
  }

  @Override public List<WeatherConfig> getWeatherConfigs() {
    return realm.where(WeatherConfig.class).findAll();
  }
}
