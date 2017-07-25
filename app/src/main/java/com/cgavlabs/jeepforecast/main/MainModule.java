package com.cgavlabs.jeepforecast.main;

import android.support.v4.app.FragmentManager;

import com.cgavlabs.jeepforecast.di.MainScope;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;

import dagger.Module;
import dagger.Provides;

@Module
public class MainModule {

    private final FragmentManager fm;

    public MainModule(FragmentManager fm) {
        this.fm = fm;
    }

    @Provides
    @MainScope
    public MainPagerAdapter providesPagerAdapter() {
        return new MainPagerAdapter(fm);
    }

    @Provides
    @MainScope
    public MainContract.Presenter providePresenter(MainContract.Interactor interactor) {
        return new MainPresenter(interactor);
    }

    @Provides
    @MainScope
    public MainContract.Interactor provideInteractor(WeatherService weatherSvc,
                                                     WeatherRepo weatherRepo) {
        return new MainInteractor(weatherSvc, weatherRepo);
    }
}
