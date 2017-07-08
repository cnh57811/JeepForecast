package com.cgavlabs.jeepforecast.di;

import android.content.Context;
import com.cgavlabs.jeepforecast.main.WeakConnectionCallbacks;
import com.cgavlabs.jeepforecast.main.WeakLocationListener;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import dagger.Module;
import dagger.Provides;
import timber.log.Timber;

@Module public class LocationModule {

  private final GoogleApiClient.ConnectionCallbacks connectionCallbacks;
  private final GoogleApiClient.OnConnectionFailedListener failedListener;
  private final LocationListener locationListener;

  public LocationModule(Context activityContext) {
    if (!(activityContext instanceof GoogleApiClient.ConnectionCallbacks)
        || !(activityContext instanceof GoogleApiClient.OnConnectionFailedListener)
        || !(activityContext instanceof LocationListener)) {
      IllegalArgumentException ex = new IllegalArgumentException(
          "Location module was passed an activity context that does not implement all required interfaces");
      Timber.e(ex);
      throw ex;
    }
    this.connectionCallbacks = (GoogleApiClient.ConnectionCallbacks) activityContext;
    this.failedListener = (GoogleApiClient.OnConnectionFailedListener) activityContext;
    this.locationListener = (LocationListener) activityContext;
  }

  @Provides GoogleApiClient provideGoogleApiClient(Context appContext) {
    WeakConnectionCallbacks w = new WeakConnectionCallbacks(connectionCallbacks, failedListener);
    return new GoogleApiClient.Builder(appContext).addConnectionCallbacks(w)
        .addOnConnectionFailedListener(w)
        .addApi(LocationServices.API)
        .build();
  }

  @Provides WeakLocationListener provideLocationListener() {
    return new WeakLocationListener(locationListener);
  }

  @Provides LocationRequest provideLocationRequest() {
    LocationRequest lr = new LocationRequest();
    lr.setInterval(600000);
    lr.setFastestInterval(5000);
    lr.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    return lr;
  }
}
