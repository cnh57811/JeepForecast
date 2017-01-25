package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.MainActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = {MainScreenModule.class, NetworkModule.class, RealmModule.class})
public interface MainScreenComponent {
  void inject(MainActivity mainActivity);
}
