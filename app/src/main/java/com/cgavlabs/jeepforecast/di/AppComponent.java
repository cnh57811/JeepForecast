package com.cgavlabs.jeepforecast.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.BitmapService;
import com.cgavlabs.jeepforecast.services.WeatherService;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;
import dagger.Component;
import javax.inject.Singleton;
import retrofit2.Retrofit;

@Singleton @Component(modules = {
    AppModule.class, NetworkModule.class, WeatherRepoModule.class, RealmModule.class,
    ImageProcessingModule.class
}) public interface AppComponent {
  Application app();

  Context context();

  SharedPrefs sharedPrefs();

  WeatherService weatherService();

  BitmapService bitmapService();

  WeatherRepo weatherRepo();
}
