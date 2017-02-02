package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;
import com.cgavlabs.jeepforecast.utils.Utils;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.inject.Inject;
import rx.Observable;

public class TodayPresenter implements TodayContract.Presenter {

  private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss z");
  private final TodayContract.View view;
  private final TodayContract.Interactor interactor;

  @Inject public TodayPresenter(TodayContract.View view, TodayContract.Interactor interactor) {
    this.view = view;
    this.interactor = interactor;
  }

  @Override public void getTodaysWeather() {
    Pair<DailyData, Currently> todaysWeather = interactor.getTodaysWeather();
    Day day = mapToDay(todaysWeather);
    view.updateTodaysWeather(day);
  }

  @Override public Observable<Bitmap> getBackgroundImage(Uri uri, int maxImgSize) {
    return interactor.getBackgroundImage(uri, maxImgSize);
  }

  private Day mapToDay(Pair<DailyData, Currently> todaysWeather) {
    DailyData data = todaysWeather.first;
    Currently curr = todaysWeather.second;
    Day day = new Day();
    if (curr != null) {
      day.setCurrentTemp(Utils.roundDouble(curr.getTemperature()));
      Date current = new Date(curr.getTime() * 1000);
      String formattedDate = SDF.format(current);
      day.setCurrentTempTime(formattedDate + "\n" + current.getTime());
    }
    if (data != null) {
      day.setHighTemp(Utils.roundDouble(data.getTemperatureMax()));
      day.setLowTemp(Utils.roundDouble(data.getTemperatureMin()));
      Date dayTime = new Date(data.getTime() * 1000);
      String formattedDate = SDF.format(dayTime);
      day.setLowTempTime(formattedDate + "\n" + dayTime.getTime());
    }
    return day;
  }
}
