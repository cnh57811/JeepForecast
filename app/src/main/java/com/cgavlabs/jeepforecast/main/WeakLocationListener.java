package com.cgavlabs.jeepforecast.main;

import android.location.Location;
import com.google.android.gms.location.LocationListener;
import java.lang.ref.WeakReference;

public class WeakLocationListener implements LocationListener {

  private WeakReference<LocationListener> weakListener;

  public WeakLocationListener(LocationListener locationListener) {
    this.weakListener = new WeakReference<>(locationListener);
  }

  @Override public void onLocationChanged(Location location) {
    if (weakListener.get() != null) {
      weakListener.get().onLocationChanged(location);
    }
  }
}
