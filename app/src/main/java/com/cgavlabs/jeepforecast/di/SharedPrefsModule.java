package com.cgavlabs.jeepforecast.di;

import android.content.Context;
import android.preference.PreferenceManager;

import com.cgavlabs.jeepforecast.utils.SharedPrefs;

import dagger.Module;
import dagger.Provides;

@Module
public class SharedPrefsModule {

    @Provides
    @ApplicationScope
    public SharedPrefs provideSharedPreferences(Context context) {
        return new SharedPrefs(PreferenceManager.getDefaultSharedPreferences(context));
    }
}
