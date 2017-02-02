package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import rx.Observable;

public interface WeatherConfigListAdapterContract {
  interface Presenter {
    Observable<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);
  }

  interface Interactor {
    Observable<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);
  }
}
