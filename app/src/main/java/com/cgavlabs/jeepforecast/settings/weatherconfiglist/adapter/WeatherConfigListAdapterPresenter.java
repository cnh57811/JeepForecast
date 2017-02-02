package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import javax.inject.Inject;
import rx.Observable;

public class WeatherConfigListAdapterPresenter
    implements WeatherConfigListAdapterContract.Presenter {

  private WeatherConfigListAdapterContract.Interactor interactor;

  @Inject
  public WeatherConfigListAdapterPresenter(WeatherConfigListAdapterContract.Interactor interactor) {
    this.interactor = interactor;
  }

  @Override public Observable<Bitmap> getThumbnailImage(String imagePath, int maxImgSize) {
    return interactor.getThumbnailImage(imagePath, maxImgSize);
  }
}
