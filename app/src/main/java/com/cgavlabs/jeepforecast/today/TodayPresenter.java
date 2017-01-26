package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.Utils;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.view.Day;
import javax.inject.Inject;

public class TodayPresenter implements Contract.Today.Presenter {

  private final Contract.Today.View view;
  private final Contract.Today.Interactor interactor;

  @Inject public TodayPresenter(Contract.Today.View view, Contract.Today.Interactor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override public void getTodaysWeather() {
    Pair<Data, Currently> todaysWeather = interactor.getTodaysWeather();
    Day day = mapToDay(todaysWeather);
    view.updateTodaysWeather(day);
  }

  private Day mapToDay(Pair<Data, Currently> todaysWeather) {
    Data data = todaysWeather.first;
    Currently curr = todaysWeather.second;
    Day day = new Day();
    day.setActualTemp(Utils.roundDouble(curr.getTemperature()));
    day.setHighTemp(Utils.roundDouble(data.getTemperatureMin()));
    day.setLowTemp(Utils.roundDouble(data.getTemperatureMax()));
    return day;
  }
}
