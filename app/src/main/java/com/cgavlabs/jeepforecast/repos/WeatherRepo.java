package com.cgavlabs.jeepforecast.repos;

import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;

public interface WeatherRepo {
  void insertOrUpdate(Weather weather);

  DailyData getLatestDailyData();

  Weather getLatestWeather(Double latitude, Double longitude);

  Currently getCurrentWeather();

  List<WeatherConfig> getWeatherConfigs();
}
