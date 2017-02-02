package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import com.cgavlabs.jeepforecast.services.BitmapService;
import javax.inject.Inject;
import rx.Observable;

class WeatherConfigListAdapterInteractor implements WeatherConfigListAdapterContract.Interactor {

  private BitmapService bitmapService;

  @Inject WeatherConfigListAdapterInteractor(BitmapService bitmapService) {
    this.bitmapService = bitmapService;
  }

  @Override public Observable<Bitmap> getThumbnailImage(String imagePath, int maxImgSize) {
    return bitmapService.scaleAndRotateBitmap(imagePath, maxImgSize);
  }
}
