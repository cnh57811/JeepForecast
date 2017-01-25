package com.cgavlabs.jeepforecast;

import android.app.Application;
import io.realm.Realm;
import io.realm.RealmConfiguration;

public class App extends Application {

  @Override public void onCreate() {
    super.onCreate();

    Realm.init(this);
    RealmConfiguration rc = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
    Realm.setDefaultConfiguration(rc);
  }
}
