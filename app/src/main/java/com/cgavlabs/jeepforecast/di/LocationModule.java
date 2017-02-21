package com.cgavlabs.jeepforecast.di;

import android.content.Context;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import dagger.Module;
import dagger.Provides;

@Module public class LocationModule {

  private Context activityContext;

  public LocationModule(Context activityContext) {
    this.activityContext = activityContext;
  }

  @Provides GoogleApiClient provideGoogleApiClient() {
    return new GoogleApiClient.Builder(activityContext).addConnectionCallbacks(
        (GoogleApiClient.ConnectionCallbacks) activityContext)
        .addOnConnectionFailedListener((GoogleApiClient.OnConnectionFailedListener) activityContext)
        .addApi(LocationServices.API)
        .build();
  }

  @Provides LocationRequest provideLocationRequest() {
    LocationRequest lr = new LocationRequest();
    lr.setInterval(600000);
    lr.setFastestInterval(5000);
    lr.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
    return lr;
  }
}
