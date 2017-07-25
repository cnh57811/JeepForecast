package com.cgavlabs.jeepforecast.di;

import com.cgavlabs.jeepforecast.services.PermissionService;
import com.cgavlabs.jeepforecast.services.PermissionServiceImpl;

import dagger.Module;
import dagger.Provides;

@Module
public class PermissionsModule {

    @Provides
    @MainScope
    public PermissionService providePermissionService() {
        return new PermissionServiceImpl();
    }
}
