package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import java.util.List;

import javax.inject.Inject;

class TodayInteractor implements TodayContract.Interactor {

    private final WeatherRepo weatherRepo;

    @Inject
    TodayInteractor(WeatherRepo weatherRepo) {
        this.weatherRepo = weatherRepo;
    }

    @Override
    public Weather getTodaysWeather(Double latitude, Double longitude) {
        return weatherRepo.getLatestWeather(latitude, longitude);
    }

    @Override
    public String getWeatherBasedImage(Weather weather) {
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
