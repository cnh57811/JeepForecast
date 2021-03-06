package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.events.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import timber.log.Timber;

public class WeatherRepoImpl implements WeatherRepo {
    private final Realm realm;

    @Inject
    public WeatherRepoImpl(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void insertOrUpdate(final Weather weather) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.insertOrUpdate(weather);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                Timber.d("new weather successfully persisted to db");
                EventBus.getDefault().post(new DataSavedEvent());
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                Timber.e(error);
            }
        });
    }

    @Override
    public DailyData getLatestDailyData() {
        int latestTime = realm.where(DailyData.class).max("time").intValue();
        return realm.where(DailyData.class).equalTo("time", latestTime).findFirst();
    }

    @Override
    public Weather getLatestWeather(Double latitude, Double longitude) {
        //int latestTime = realm.where(Currently.class).max("time").intValue();
        Timber.d("getLatestWeather: Lat:%s Lng:%s", latitude, longitude);
        Timber.d("Start getLatestWeather query");
        Weather w =
                realm.where(Weather.class).equalTo("latitude", latitude).equalTo("longitude", longitude)
                        //.equalTo("currently.time", latestTime)
                        .findFirst();
        Timber.d("End getLatestWeather query");
        return w;
    }

    @Override
    public Currently getCurrentWeather() {
        return realm.where(Currently.class).findFirst();
    }

    @Override
    public List<WeatherConfig> getWeatherConfigs() {
        return realm.where(WeatherConfig.class).findAll();
    }

    @Override
    public void insertOrUpdate(WeatherConfig wc) {
        if (wc.getId() == null) {
            int key = getNextKey();
            wc.setId(key);
        }
        Timber.d(wc.toString());
        realm.executeTransaction(realm1 -> realm1.insertOrUpdate(wc));
    }

    @Override
    public List<WeatherConfig> getAllWeatherConfigs() {
        return realm.where(WeatherConfig.class).findAll();
    }

    private int getNextKey() {
        Number n = realm.where(WeatherConfig.class).max("id");
        if (n != null) {
            return n.intValue() + 1;
        } else {
            return 0;
        }
    }
}
