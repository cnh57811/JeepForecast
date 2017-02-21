package com.cgavlabs.jeepforecast.services;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import javax.inject.Inject;
import timber.log.Timber;

public class PermissionServiceImpl implements PermissionService {

  private Context context;

  @Inject public PermissionServiceImpl(Context context) {
    this.context = context;
  }

  @Override public boolean hasLocationPermissions() {
    boolean hasFineLocationPermissions =
        ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED;
    return !requiresRuntimePermissionCheck() || hasFineLocationPermissions;
  }

  @Override public void requestLocationPermissions(Activity activity) {
    ActivityCompat.requestPermissions(activity,
        new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION },
        PERMISSION_ACCESS_LOCATION);
  }

  @Override public boolean hasLocationPermissions(String[] permissions, int[] grantResults) {
    if (!requiresRuntimePermissionCheck()) {
      return true;
    }
    for (int i = 0; i < permissions.length; i++) {
      if (Manifest.permission.ACCESS_FINE_LOCATION.equals(permissions[i])
          && PackageManager.PERMISSION_GRANTED == grantResults[i]) {
        Timber.d("Location permissions have been granted by user!");
        return true;
      }
    }
    return false;
  }

  private boolean requiresRuntimePermissionCheck() {
    return Build.VERSION.SDK_INT >= Build.VERSION_CODES.M;
  }
}
