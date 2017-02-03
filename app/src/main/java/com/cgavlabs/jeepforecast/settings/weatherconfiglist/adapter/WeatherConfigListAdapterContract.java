package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import rx.Single;

public interface WeatherConfigListAdapterContract {
  interface Presenter {
    Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);
  }

  interface Interactor {
    Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);
  }
}
