package com.cgavlabs.jeepforecast.services;

import android.app.Activity;
import android.content.Context;

public interface PermissionService {
  int PERMISSION_ACCESS_LOCATION = 1;
  int PERMISSION_ACCESS_STORAGE = 2;

  boolean hasLocationPermissions(Context context);

  boolean hasLocationPermissions(String[] permissions, int[] grantResults);

  void requestLocationPermissions(Activity activity);

  boolean hasStoragePermissions(Context context);

  void requestStoragePermissions(Activity activity);
}
