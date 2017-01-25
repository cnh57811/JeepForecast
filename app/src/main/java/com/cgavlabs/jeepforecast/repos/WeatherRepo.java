package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.domain.Weather;

public interface WeatherRepo {
  void insertOrUpdate(Weather weather);

  Data getTodaysWeather();

  Currently getCurrentWeather();
}
