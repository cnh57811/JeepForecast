package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import com.cgavlabs.jeepforecast.models.domain.Weather;
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

  @Override public Weather getTodaysWeather(Double latitude, Double longitude) {
    return weatherRepo.getLatestWeather(latitude, longitude);
  }

  @Override public Single<Bitmap> getBackgroundImage(Uri uri, int maxImgSize) {
    return bitmapSvc.scaleAndRotateBitmap(uri, maxImgSize);
  }
}
