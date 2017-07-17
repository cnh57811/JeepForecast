package com.cgavlabs.jeepforecast.di;

import android.content.Context;
import android.preference.PreferenceManager;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;
import dagger.Module;
import dagger.Provides;

@Module public class ContextModule {

  private final Context context;

  public ContextModule(Context context) {
    this.context = context;
  }

  @Provides @ApplicationScope public Context provideContext() {
    return context;
  }

  @Provides @ApplicationScope public SharedPrefs provideSharedPreferences() {
    return new SharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
  }
}
