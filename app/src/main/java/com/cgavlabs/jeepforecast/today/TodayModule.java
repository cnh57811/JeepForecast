package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import javax.inject.Singleton;

@Module
public class TodayModule {

  private final Contract.Today.View view;

  public TodayModule(Contract.Today.View view) {
    this.view = view;
  }

  @Provides
  public Contract.Today.Presenter providePresenter(Contract.Today.Interactor interactor) {
    return new TodayPresenter(view, interactor);
  }

  @Provides
  public Contract.Today.Interactor provideInteractor(WeatherRepo weatherRepo) {
    return new TodayInteractor(weatherRepo);
  }

}
