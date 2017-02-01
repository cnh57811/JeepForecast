package com.cgavlabs.jeepforecast.di;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import javax.inject.Singleton;

@Module public class RealmModule {

  @Provides @Singleton public Realm provideRealm() {
    return Realm.getDefaultInstance();
  }
}
