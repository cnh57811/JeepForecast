package com.cgavlabs.jeepforecast.di;

import android.content.Context;

import com.cgavlabs.jeepforecast.services.BitmapService;
import com.cgavlabs.jeepforecast.services.BitmapServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class ImageProcessingModule {
    @Provides
    @ApplicationScope
    public BitmapService provideBitmapService(Context context) {
        return new BitmapServiceImpl(context);
    }
}
