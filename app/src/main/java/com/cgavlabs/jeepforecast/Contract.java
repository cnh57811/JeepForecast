package com.cgavlabs.jeepforecast;

public interface Contract {
  interface Main {
    interface Presenter {
      void callWeather(Double latitude, Double longitude);
    }

    interface Interactor {
      void callWeather(Double latitude, Double longitude);
    }
  }
}
