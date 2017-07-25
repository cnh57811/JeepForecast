package com.cgavlabs.jeepforecast.main;

import android.location.Location;

import com.google.android.gms.location.LocationListener;

import java.lang.ref.WeakReference;

/**
 * GoogleApiClient has a bug where it does not release the Activity/Fragment/Context and causes
 * a memory leak.  Any interface implementation passed to the GoogleApiClient must be wrapped in a
 * WeakReference to prevent this memory leak from occuring.
 */
public class WeakLocationListener implements LocationListener {

    private WeakReference<LocationListener> weakListener;

    public WeakLocationListener(LocationListener locationListener) {
        this.weakListener = new WeakReference<>(locationListener);
    }

    @Override
    public void onLocationChanged(Location location) {
        if (weakListener.get() != null) {
            weakListener.get().onLocationChanged(location);
        }
    }
}
