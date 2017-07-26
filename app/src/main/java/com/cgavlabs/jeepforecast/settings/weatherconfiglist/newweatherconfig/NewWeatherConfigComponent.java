package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.WeatherConfigScope;

import dagger.Component;

@WeatherConfigScope
@Component(dependencies = {AppComponent.class}, modules = {NewWeatherConfigModule.class})
public interface NewWeatherConfigComponent {
    void inject(NewWeatherConfigFragment fragment);

    void inject(EditWeatherConfigFragment fragment);
}
