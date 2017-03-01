package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.Day;
import rx.Single;

public interface TodayContract {
  interface View {
    void updateTodaysWeather(Day day);
  }

  interface Presenter {
    void getTodaysWeather(Double latitude, Double longitude);

    Single<Bitmap> getBackgroundImage(Uri uri, int maxImgSize);
  }

  interface Interactor {
    Weather getTodaysWeather(Double latitude, Double longitude);

    Single<Bitmap> getBackgroundImage(Uri uri, int maxImgSize);

    String getWeatherBasedImage(Weather weather);
  }
}
