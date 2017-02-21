package com.cgavlabs.jeepforecast.services;

import android.app.Activity;

public interface PermissionService {
  int PERMISSION_ACCESS_LOCATION = 1;

  boolean hasLocationPermissions();

  boolean hasLocationPermissions(String[] permissions, int[] grantResults);

  void requestLocationPermissions(Activity activity);
}
