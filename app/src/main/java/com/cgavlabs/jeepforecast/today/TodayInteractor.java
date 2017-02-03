package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.BitmapService;
import javax.inject.Inject;
import rx.Single;

public class TodayInteractor implements TodayContract.Interactor {

  private final WeatherRepo weatherRepo;
  private final BitmapService bitmapSvc;

  @Inject public TodayInteractor(BitmapService bitmapSvc, WeatherRepo weatherRepo) {
    this.bitmapSvc = bitmapSvc;
    this.weatherRepo = weatherRepo;
  }

  @Override public Pair<DailyData, Currently> getTodaysWeather() {
    DailyData todaysWeather = weatherRepo.getTodaysWeather();
    Currently currentWeather = weatherRepo.getCurrentWeather();
    return Pair.create(todaysWeather, currentWeather);
  }

  @Override public Single<Bitmap> getBackgroundImage(Uri uri, int maxImgSize) {
    return bitmapSvc.scaleAndRotateBitmap(uri, maxImgSize);
  }
}
