package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import javax.inject.Inject;

public class TodayInteractor implements Contract.Today.Interactor {

  private final WeatherRepo weatherRepo;

  @Inject public TodayInteractor(WeatherRepo weatherRepo) {
    this.weatherRepo = weatherRepo;
  }

  @Override public Pair<Data, Currently> getTodaysWeather() {
    Data todaysWeather = weatherRepo.getTodaysWeather();
    Currently currentWeather = weatherRepo.getCurrentWeather();
    return Pair.create(todaysWeather, currentWeather);
  }
}
