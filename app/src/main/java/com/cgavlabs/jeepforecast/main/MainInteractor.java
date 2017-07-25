package com.cgavlabs.jeepforecast.main;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

class MainInteractor implements MainContract.Interactor {

    static {
        System.loadLibrary("keys");
    }

    private final WeatherService weatherSvc;
    private final WeatherRepo weatherRepo;

    @Inject
    MainInteractor(WeatherService weatherSvc, WeatherRepo weatherRepo) {
        this.weatherSvc = weatherSvc;
        this.weatherRepo = weatherRepo;
    }

    private native String getDarkSkyKey();

    @Override
    public void callWeather(Double latitude, Double longitude) {
        weatherSvc.getWeather(getDarkSkyKey(), latitude, longitude)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(weather -> weatherRepo.insertOrUpdate(weather), e -> Timber.e(e),
                        () -> Timber.d("callWeather onCompleted()"));
    }
}
