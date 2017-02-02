package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;
import rx.Observable;

public interface TodayContract {
  interface View {
    void updateTodaysWeather(Day day);
  }

  interface Presenter {
    void getTodaysWeather();

    Observable<Bitmap> getBackgroundImage(Uri uri, int maxImgSize);
  }

  interface Interactor {
    Pair<DailyData, Currently> getTodaysWeather();

    Observable<Bitmap> getBackgroundImage(Uri uri, int maxImgSize);
  }
}
