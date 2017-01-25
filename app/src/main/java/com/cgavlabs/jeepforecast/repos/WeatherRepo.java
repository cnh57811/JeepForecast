package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.models.domain.Weather;

public interface WeatherRepo {
  void insertOrUpdate(Weather weather);
}
