package com.cgavlabs.jeepforecast.di;

import android.app.Application;
import android.content.SharedPreferences;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.BitmapService;
import com.cgavlabs.jeepforecast.services.WeatherService;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton @Component(modules = {
    AppModule.class, NetworkModule.class, WeatherRepoModule.class, RealmModule.class,
    ImageProcessingModule.class
}) public interface AppComponent {
  Application app();

  SharedPreferences sharedPreferences();

  Retrofit retrofit();

  WeatherService weatherService();

  BitmapService bitmapService();

  WeatherRepo weatherRepo();
}
