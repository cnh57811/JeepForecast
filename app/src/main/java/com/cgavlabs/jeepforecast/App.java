package com.cgavlabs.jeepforecast;

import android.app.Application;

import com.cgavlabs.jeepforecast.di.AppComponent;
import com.cgavlabs.jeepforecast.di.ContextModule;
import com.cgavlabs.jeepforecast.di.DaggerAppComponent;
import com.squareup.leakcanary.LeakCanary;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import timber.log.Timber;

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);

        appComponent = DaggerAppComponent.builder().contextModule(new ContextModule(this)).build();

        Realm.init(this);
        RealmConfiguration rc = new RealmConfiguration.Builder().deleteRealmIfMigrationNeeded().build();
        Realm.setDefaultConfiguration(rc);

        Timber.plant(new Timber.DebugTree() {
            @Override
            protected String createStackElementTag(StackTraceElement e) {
                return super.createStackElementTag(e) + ":" + e.getLineNumber();
            }
        });
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
