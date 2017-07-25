package com.cgavlabs.jeepforecast.main;

public interface MainContract {
    interface Presenter {
        void callWeather(Double latitude, Double longitude);
    }

    interface Interactor {
        void callWeather(Double latitude, Double longitude);
    }
}
