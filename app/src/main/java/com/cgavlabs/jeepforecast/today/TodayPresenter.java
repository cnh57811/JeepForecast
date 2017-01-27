package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.Utils;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;
import javax.inject.Inject;
import timber.log.Timber;

public class TodayPresenter implements Contract.Today.Presenter {

  private final Contract.Today.View view;
  private final Contract.Today.Interactor interactor;

  @Inject public TodayPresenter(Contract.Today.View view, Contract.Today.Interactor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override public void getTodaysWeather() {
    Pair<DailyData, Currently> todaysWeather = interactor.getTodaysWeather();
    Day day = mapToDay(todaysWeather);
    view.updateTodaysWeather(day);
  }

  private Day mapToDay(Pair<DailyData, Currently> todaysWeather) {
    DailyData data = todaysWeather.first;
    Currently curr = todaysWeather.second;
    Day day = new Day();
    if(curr != null) {
      day.setActualTemp(Utils.roundDouble(curr.getTemperature()));
    }
    if(data != null) {
      day.setHighTemp(Utils.roundDouble(data.getTemperatureMax()));
      day.setLowTemp(Utils.roundDouble(data.getTemperatureMin()));
    }
    return day;
  }
}
