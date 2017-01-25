package com.cgavlabs.jeepforecast.main;

import android.support.v4.app.FragmentManager;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.repos.WeatherRepo;
import com.cgavlabs.jeepforecast.services.WeatherService;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class MainModule {

  private final FragmentManager fm;

  public MainModule(FragmentManager fm) {
    this.fm = fm;
  }

  @Provides @Singleton public MainPagerAdapter providesPagerAdapter() {
    return new MainPagerAdapter(fm);
  }

  @Provides public Contract.Main.Presenter providePresenter(Contract.Main.Interactor interactor) {
    return new MainPresenter(interactor);
  }

  @Provides public Contract.Main.Interactor provideInteractor(WeatherService weatherSvc,
      WeatherRepo weatherRepo) {
    return new MainInteractor(weatherSvc, weatherRepo);
  }
}
