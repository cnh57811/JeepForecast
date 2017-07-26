package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.WeatherConfigScope;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter.WeatherConfigListAdapterModule;

import dagger.Component;

@WeatherConfigScope
@Component(dependencies = {AppComponent.class}, modules = {
        WeatherConfigModule.class, WeatherConfigListAdapterModule.class
})
public interface WeatherConfigComponent {
    void inject(WeatherConfigListActivity activity);
}
