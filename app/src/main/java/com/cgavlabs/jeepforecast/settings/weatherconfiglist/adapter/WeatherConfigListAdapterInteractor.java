package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.BitmapService;

import java.util.List;

import javax.inject.Inject;

import rx.Single;

class WeatherConfigListAdapterInteractor implements WeatherConfigListAdapterContract.Interactor {

    private final WeatherRepo weatherRepo;
    private BitmapService bitmapService;

    @Inject
    WeatherConfigListAdapterInteractor(BitmapService bitmapService, WeatherRepo weatherRepo) {
        this.bitmapService = bitmapService;
        this.weatherRepo = weatherRepo;
    }

    @Override
    public Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize) {
        return bitmapService.scaleAndRotateBitmap(imagePath, maxImgSize);
    }

    @Override
    public List<WeatherConfig> getAllWeatherConfigs() {
        return weatherRepo.getAllWeatherConfigs();
    }

    @Override
    public void setThumbnailImage(String imagePath, ImageView imageView) {
        bitmapService.setThumbnailImage(imagePath, imageView);
    }
}
