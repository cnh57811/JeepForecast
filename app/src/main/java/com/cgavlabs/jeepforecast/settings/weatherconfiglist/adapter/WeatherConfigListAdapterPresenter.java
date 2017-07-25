package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

import javax.inject.Inject;

import rx.Single;

class WeatherConfigListAdapterPresenter implements WeatherConfigListAdapterContract.Presenter {

    private WeatherConfigListAdapterContract.Interactor interactor;

    @Inject
    WeatherConfigListAdapterPresenter(
            WeatherConfigListAdapterContract.Interactor interactor) {
        this.interactor = interactor;
    }

    @Override
    public Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize) {
        return interactor.getThumbnailImage(imagePath, maxImgSize);
    }

    @Override
    public List<WeatherConfig> getAllWeatherConfigs() {
        return interactor.getAllWeatherConfigs();
    }

    @Override
    public void setThumbnailImage(String imagePath, ImageView imageView) {
        interactor.setThumbnailImage(imagePath, imageView);
    }
}
