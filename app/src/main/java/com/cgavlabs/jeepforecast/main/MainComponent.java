package com.cgavlabs.jeepforecast.main;

import com.cgavlabs.jeepforecast.di.NetworkModule;
import com.cgavlabs.jeepforecast.di.RealmModule;
import com.cgavlabs.jeepforecast.di.WeatherRepoModule;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = {
    MainModule.class, NetworkModule.class, RealmModule.class, WeatherRepoModule.class
}) public interface MainComponent {
  void inject(MainActivity mainActivity);
}
