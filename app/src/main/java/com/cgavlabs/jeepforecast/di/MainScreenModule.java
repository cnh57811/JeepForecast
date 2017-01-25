package com.cgavlabs.jeepforecast.di;

import android.support.v4.app.FragmentManager;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.MainInteractor;
import com.cgavlabs.jeepforecast.MainPresenter;
import com.cgavlabs.jeepforecast.PagerAdapter;
import com.cgavlabs.jeepforecast.WeatherService;
import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import javax.inject.Singleton;

@Module public class MainScreenModule {

  private final FragmentManager fm;

  public MainScreenModule(FragmentManager fm) {
    this.fm = fm;
  }

  @Provides @Singleton public PagerAdapter providesPagerAdapter() {
    return new PagerAdapter(fm);
  }

  @Provides public Contract.Main.Presenter providePresenter(Contract.Main.Interactor interactor) {
    return new MainPresenter(interactor);
  }

  @Provides public Contract.Main.Interactor provideInteractor(WeatherService weatherSvc, Realm realm) {
    return new MainInteractor(weatherSvc, realm);
  }

}
