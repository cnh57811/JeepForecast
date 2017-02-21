package com.cgavlabs.jeepforecast.di;

import android.content.Context;
import com.cgavlabs.jeepforecast.services.PermissionService;
import com.cgavlabs.jeepforecast.services.PermissionServiceImpl;
import dagger.Module;
import dagger.Provides;

@Module public class PermissionsModule {

  @Provides public PermissionService providePermissionService(Context context) {
    return new PermissionServiceImpl(context);
  }
}
