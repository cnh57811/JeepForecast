package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.WeatherService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class NetworkModule {

  @Provides public Retrofit provideRetrofit() {
    return new Retrofit.Builder().baseUrl("https://api.darksky.net/forecast/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  @Provides public WeatherService providesWeatherService(Retrofit retrofit) {
    return retrofit.create(WeatherService.class);
  }
}
