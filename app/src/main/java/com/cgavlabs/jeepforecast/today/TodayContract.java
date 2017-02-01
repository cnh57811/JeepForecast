package com.cgavlabs.jeepforecast.today;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;

public interface TodayContract {
  interface View {
    void updateTodaysWeather(Day day);
  }

  interface Presenter {
    void getTodaysWeather();
  }

  interface Interactor {
    Pair<DailyData, Currently> getTodaysWeather();
  }
}
