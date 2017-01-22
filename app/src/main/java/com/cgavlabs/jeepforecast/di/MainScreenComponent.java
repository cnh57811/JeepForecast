package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.MainActivity;
import dagger.Component;

@Component(modules = {MainScreenModule.class, NetworkModule.class})
public interface MainScreenComponent {
  void inject(MainActivity mainActivity);
}
