package com.cgavlabs.jeepforecast.di;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {

  private final Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton public Application provideApplication() {
    return application;
  }

  @Provides @Singleton public Context provideContext(Application application) {
    return application;
  }

  @Provides @Singleton public SharedPrefs provideSharedPreferences(Application application) {
    return new SharedPrefs(PreferenceManager.getDefaultSharedPreferences(application));
  }
}
