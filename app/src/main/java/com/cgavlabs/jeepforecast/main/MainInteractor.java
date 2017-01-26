package com.cgavlabs.jeepforecast.main;

import android.util.Log;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.models.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainInteractor implements Contract.Main.Interactor {

  static {
    System.loadLibrary("keys");
  }

  private final WeatherService weatherSvc;
  private final WeatherRepo weatherRepo;

  @Inject public MainInteractor(WeatherService weatherSvc, WeatherRepo weatherRepo) {
    this.weatherSvc = weatherSvc;
    this.weatherRepo = weatherRepo;
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
            weatherRepo.insertOrUpdate(weather);
          }
        });
  }
}
