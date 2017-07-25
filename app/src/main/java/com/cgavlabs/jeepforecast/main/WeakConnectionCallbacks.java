package com.cgavlabs.jeepforecast.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import java.lang.ref.WeakReference;

import timber.log.Timber;

/**
 * GoogleApiClient has a bug where it does not release the Activity/Fragment/Context and causes
 * a memory leak.  Any class passed to the GoogleApiClient must be wrapped in a WeakReference to
 * prevent this memory leak from occuring.
 */
public class WeakConnectionCallbacks
        implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private WeakReference<GoogleApiClient.ConnectionCallbacks> connectionCallbacks;
    private WeakReference<GoogleApiClient.OnConnectionFailedListener> failedListener;

    public WeakConnectionCallbacks(GoogleApiClient.ConnectionCallbacks connectionCallbacks,
                                   GoogleApiClient.OnConnectionFailedListener failedListener) {
        this.connectionCallbacks = new WeakReference<>(connectionCallbacks);
        this.failedListener = new WeakReference<>(failedListener);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        Timber.d("WEAK onConnected");
        if (connectionCallbacks != null) {
            connectionCallbacks.get().onConnected(bundle);
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        Timber.d("WEAK onConnectionSuspended");
        if (connectionCallbacks != null) {
            connectionCallbacks.get().onConnectionSuspended(i);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Timber.d("WEAK onConnectionFailed");
        if (failedListener != null) {
            failedListener.get().onConnectionFailed(connectionResult);
        }
    }
}
