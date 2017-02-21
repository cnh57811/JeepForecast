package com.cgavlabs.jeepforecast.main;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.LocationModule;
import com.cgavlabs.jeepforecast.di.PermissionsModule;
import com.cgavlabs.jeepforecast.di.UserScope;
import dagger.Component;

@UserScope @Component(dependencies = { AppComponent.class }, modules = {
    MainModule.class, LocationModule.class, PermissionsModule.class
}) public interface MainComponent {
  void inject(MainActivity mainActivity);
}
