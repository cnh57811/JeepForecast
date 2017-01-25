package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.WeatherService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module public class NetworkModule {

  @Provides @Singleton public Retrofit provideRetrofit() {
    return new Retrofit.Builder().baseUrl("https://api.darksky.net/forecast/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .build();
  }

  @Provides @Singleton public WeatherService provideWeatherService(Retrofit retrofit) {
    return retrofit.create(WeatherService.class);
  }
}
