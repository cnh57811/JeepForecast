package com.cgavlabs.jeepforecast.di;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module public class RealmModule {

  @Provides @ApplicationScope public Realm provideRealm() {
    return Realm.getDefaultInstance();
  }
}
