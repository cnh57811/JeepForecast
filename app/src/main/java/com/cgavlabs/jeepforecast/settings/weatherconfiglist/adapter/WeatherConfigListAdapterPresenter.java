package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import javax.inject.Inject;
import rx.Single;

class WeatherConfigListAdapterPresenter implements WeatherConfigListAdapterContract.Presenter {

  private WeatherConfigListAdapterContract.Interactor interactor;

  @Inject WeatherConfigListAdapterPresenter(
      WeatherConfigListAdapterContract.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize) {
    return interactor.getThumbnailImage(imagePath, maxImgSize);
  }
}
