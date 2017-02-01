package com.cgavlabs.jeepforecast.main;

import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;
import javax.inject.Inject;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class MainInteractor implements MainContract.Interactor {

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
            Timber.d("onCompleted");
          }

          @Override public void onError(Throwable e) {
            Timber.e(e);
          }

          @Override public void onNext(final Weather weather) {
            Timber.d("Weather successfully retrieved from network");
            weatherRepo.insertOrUpdate(weather);
          }
        });
  }
}
