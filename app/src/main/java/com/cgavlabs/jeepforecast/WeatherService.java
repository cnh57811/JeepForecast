package com.cgavlabs.jeepforecast;

import com.cgavlabs.jeepforecast.models.domain.Weather;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

public interface WeatherService {
  @GET("{key}/{latitude},{longitude}") Observable<Weather> getWeather(@Path("key") String key,
      @Path("latitude") Double lat, @Path("longitude") Double lng);
}
