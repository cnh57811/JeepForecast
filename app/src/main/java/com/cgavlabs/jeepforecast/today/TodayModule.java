package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.di.TodayScope;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;

import dagger.Module;
import dagger.Provides;

@Module
public class TodayModule {

    private final TodayContract.View view;

    public TodayModule(TodayContract.View view) {
        this.view = view;
    }

    @TodayScope
    @Provides
    public TodayContract.Presenter providePresenter(TodayContract.Interactor interactor) {
        return new TodayPresenter(view, interactor);
    }

    @TodayScope
    @Provides
    public TodayContract.Interactor provideInteractor(WeatherRepo weatherRepo) {
        return new TodayInteractor(weatherRepo);
    }
}
