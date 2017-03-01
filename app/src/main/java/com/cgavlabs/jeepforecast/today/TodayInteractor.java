package com.cgavlabs.jeepforecast.today;

import android.graphics.Bitmap;
import android.net.Uri;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.BitmapService;
import java.util.List;
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

  @Override public String getWeatherBasedImage(Weather weather) {
    List<WeatherConfig> weatherConfigs = weatherRepo.getAllWeatherConfigs();
    String imageUri = null;
    Double currentTemp = weather.getCurrently().getTemperature();
    for (WeatherConfig wc : weatherConfigs) {
      if (currentTemp <= wc.getHighTemp() && currentTemp >= wc.getLowTemp()) {
        imageUri = wc.getImagePath();
      }
    }
    return imageUri;
  }
}
