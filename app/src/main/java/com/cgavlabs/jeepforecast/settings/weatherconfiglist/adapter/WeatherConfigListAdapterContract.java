package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

import rx.Single;

public interface WeatherConfigListAdapterContract {
    interface Presenter {
        Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);

        List<WeatherConfig> getAllWeatherConfigs();

        void setThumbnailImage(String imagePath, ImageView imageView);
    }

    interface Interactor {
        Single<Bitmap> getThumbnailImage(String imagePath, int maxImgSize);

        List<WeatherConfig> getAllWeatherConfigs();

        void setThumbnailImage(String imagePath, ImageView imageView);
    }
}
