package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.Day;

public interface TodayContract {
    interface View {
        void updateTodaysWeather(Day day);
    }

    interface Presenter {
        void getTodaysWeather(Double latitude, Double longitude);
    }

    interface Interactor {
        Weather getTodaysWeather(Double latitude, Double longitude);

        String getWeatherBasedImage(Weather weather);
    }
}
