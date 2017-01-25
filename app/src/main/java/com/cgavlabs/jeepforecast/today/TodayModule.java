package com.cgavlabs.jeepforecast.today;

import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import javax.inject.Singleton;

@Module
public class TodayModule {

  @Provides
  public Contract.Today.Presenter providePresenter(Contract.Today.Interactor interactor) {
    return new TodayPresenter(interactor);
  }

  @Provides
  public Contract.Today.Interactor provideInteractor(WeatherRepo weatherRepo) {
    return new TodayInteractor(weatherRepo);
  }

}
