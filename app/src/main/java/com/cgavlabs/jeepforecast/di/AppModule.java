package com.cgavlabs.jeepforecast.di;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module public class AppModule {

  Application application;

  public AppModule(Application application) {
    this.application = application;
  }

  @Provides @Singleton public Application provideApplication() {
    return application;
  }

  @Provides @Singleton public Context provideContext(Application application) {
    return application;
  }

  @Provides @Singleton public SharedPreferences provideSharedPreferences(Application application) {
    return PreferenceManager.getDefaultSharedPreferences(application);
  }
}
