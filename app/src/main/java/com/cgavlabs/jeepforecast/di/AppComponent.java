package com.cgavlabs.jeepforecast.di;

import android.content.Context;

import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;

import dagger.Component;

@ApplicationScope
@Component(modules = {
        ContextModule.class,
        NetworkModule.class,
        WeatherRepoModule.class,
        RealmModule.class,
        SharedPrefsModule.class
})
public interface AppComponent {
    Context context();

    SharedPrefs sharedPrefs();

    WeatherService weatherService();

    WeatherRepo weatherRepo();
}
