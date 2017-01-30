package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.utils.Utils;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;

public class TodayPresenter implements Contract.Today.Presenter {

  private static final String DATE_FORMAT = "yyyy.MM.dd 'at' HH:mm:ss z";
  private static final SimpleDateFormat SDF = new SimpleDateFormat(DATE_FORMAT);
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
    if (curr != null) {
      day.setCurrentTemp(Utils.roundDouble(curr.getTemperature()));
      Date current = new Date(curr.getTime()*1000);
      String formattedDate = SDF.format(current);
      day.setCurrentTempTime(formattedDate + "\n" + current.getTime());
    }
    if (data != null) {
      day.setHighTemp(Utils.roundDouble(data.getTemperatureMax()));
      day.setLowTemp(Utils.roundDouble(data.getTemperatureMin()));
      Date dayTime = new Date(data.getTime()*1000);
      String formattedDate = SDF.format(dayTime);
      day.setLowTempTime(formattedDate + "\n" + dayTime.getTime());
    }
    return day;
  }
}
