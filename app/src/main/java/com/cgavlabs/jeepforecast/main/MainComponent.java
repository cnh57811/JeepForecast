package com.cgavlabs.jeepforecast.main;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.LocationModule;
import com.cgavlabs.jeepforecast.di.MainScope;
import com.cgavlabs.jeepforecast.di.PermissionsModule;

import dagger.Component;

@MainScope
@Component(dependencies = AppComponent.class, modules = {
        MainModule.class, LocationModule.class, PermissionsModule.class
})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
