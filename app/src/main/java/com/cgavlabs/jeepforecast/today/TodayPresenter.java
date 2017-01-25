package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.view.Day;
import javax.inject.Inject;

public class TodayPresenter implements Contract.Today.Presenter {

  private final Contract.Today.Interactor interactor;

  @Inject public TodayPresenter(Contract.Today.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public Day getTodaysWeather() {
    Pair<Data, Currently> todaysWeather = interactor.getTodaysWeather();
    Day day = mapToDay(todaysWeather);
    return day;
  }

  private Day mapToDay(Pair<Data, Currently> todaysWeather) {
    return new Day();
  }
}
