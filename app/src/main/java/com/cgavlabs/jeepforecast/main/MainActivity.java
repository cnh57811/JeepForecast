package com.cgavlabs.jeepforecast.main;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseActivity;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.di.LocationModule;
import com.cgavlabs.jeepforecast.services.PermissionService;
import com.cgavlabs.jeepforecast.settings.weatherconfiglist.WeatherConfigListActivity;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import javax.inject.Inject;
import timber.log.Timber;

import static com.cgavlabs.jeepforecast.Constants.INTENT_EXTRA_LATITUDE;
import static com.cgavlabs.jeepforecast.Constants.INTENT_EXTRA_LONGITUDE;

public class MainActivity extends BaseActivity
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,
    LocationListener {

  @Inject MainContract.Presenter presenter;
  @Inject MainPagerAdapter pagerAdapter;
  @Inject GoogleApiClient googleApiClient;
  @Inject LocationRequest locationRequest;
  @Inject PermissionService permissionSvc;
  @Inject SharedPrefs sharedPrefs;
  @BindView(R.id.toolbar) Toolbar toolbar;
  @BindView(R.id.viewPager) ViewPager viewPager;
  @BindView(R.id.tabs) TabLayout tabs;
  private boolean requestingLocationUpdates;

  @Override protected void onCreate(Bundle savedInstanceState) {
    Timber.d("onCreate()");
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    setupViews();
    handleIntent();
  }

  @Override protected void onStart() {
    Timber.d("onStart");
    super.onStart();
    googleApiClient.connect();
  }

  @Override protected void onResume() {
    Timber.d("onResume");
    super.onResume();
    startLocationUpdates();
  }

  @Override protected void onPause() {
    Timber.d("onPause");
    super.onPause();
    stopLocationUpdates();
  }

  @Override protected void onStop() {
    Timber.d("onStop");
    super.onStop();
    googleApiClient.disconnect();
  }

  private void setupViews() {
    setSupportActionBar(toolbar);
    viewPager.setAdapter(pagerAdapter);
    tabs.setupWithViewPager(viewPager);
  }

  private void handleIntent() {
    Timber.d("handleIntent");
    Intent i = getIntent();
    if (i != null && i.hasExtra(INTENT_EXTRA_LATITUDE) && i.hasExtra(INTENT_EXTRA_LONGITUDE)) {
      // lat and long were passed from the search bar
      Double latitude = i.getDoubleExtra(INTENT_EXTRA_LATITUDE, sharedPrefs.getLatitude());
      Double longitude = i.getDoubleExtra(INTENT_EXTRA_LONGITUDE, sharedPrefs.getLongitude());
      Timber.d("Lat:%s put into shared prefs", latitude);
      Timber.d("Lng:%s put into shared prefs", longitude);
      sharedPrefs.putLatitude(latitude);
      sharedPrefs.putLongitude(longitude);
      presenter.callWeather(latitude, longitude);
      sharedPrefs.putIsUsingCurrentLocation(false);
    }
  }

  @SuppressWarnings("MissingPermission") @Override
  public void onConnected(@Nullable Bundle bundle) {
    Timber.d("onConnected");
    if (permissionSvc.hasLocationPermissions()) {
      startLocationUpdates();
    } else {
      permissionSvc.requestLocationPermissions(this);
    }
  }

  @Override public void onConnectionSuspended(int i) {
    Timber.e("GoogleApiClient connection suspended");
  }

  @Override public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    Timber.e("GoogleApiClient connection failed");
  }

  @SuppressWarnings("MissingPermission") private void getWeatherForLastLocation() {
    Timber.d("getWeatherForLastLocation()");
    Location lastLocation = null;
    if (sharedPrefs.isUsingCurrentLocation() && permissionSvc.hasLocationPermissions()) {
      lastLocation = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
    }
    double latitude;
    double longitude;
    if (lastLocation != null) {
      latitude = lastLocation.getLatitude();
      longitude = lastLocation.getLongitude();
      sharedPrefs.putLatitude(latitude);
      sharedPrefs.putLongitude(longitude);
    } else {
      latitude = sharedPrefs.getLatitude();
      longitude = sharedPrefs.getLongitude();
    }
    presenter.callWeather(latitude, longitude);
  }

  @SuppressWarnings("MissingPermission") private void startLocationUpdates() {
    Timber.d("startLocationUpdates");
    if (sharedPrefs.isUsingCurrentLocation()
        && permissionSvc.hasLocationPermissions()
        && googleApiClient.isConnected()) {
      LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest,
          this);
      requestingLocationUpdates = true;
    }
  }

  @SuppressWarnings("MissingPermission") private void stopLocationUpdates() {
    Timber.d("stopLocationUpdates");
    if (permissionSvc.hasLocationPermissions()
        && googleApiClient.isConnected()
        && requestingLocationUpdates) {
      LocationServices.FusedLocationApi.removeLocationUpdates(googleApiClient, this);
      requestingLocationUpdates = false;
    }
  }

  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    Timber.d("onRequestPermissionsResult");
    boolean locationPermissionGranted = false;
    if (PermissionService.PERMISSION_ACCESS_LOCATION == requestCode) {
      locationPermissionGranted = permissionSvc.hasLocationPermissions(permissions, grantResults);
    }
    if (locationPermissionGranted) {
      getWeatherForLastLocation();
      startLocationUpdates();
    }
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);
  }

  @Override public void onLocationChanged(Location location) {
    Timber.d("onLocationChanged");
    if (sharedPrefs.isUsingCurrentLocation()) {
      if (location != null) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();
        Timber.d("New location coords: Lat:%s Lng:%s", latitude, longitude);
        sharedPrefs.putLatitude(latitude);
        sharedPrefs.putLongitude(longitude);
        presenter.callWeather(latitude, longitude);
      }
    }
  }

  @OnClick(R.id.btn_use_current_location) public void useCurrentLocation() {
    sharedPrefs.putIsUsingCurrentLocation(true);
    getWeatherForLastLocation();
    startLocationUpdates();
  }

  @Override public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
    SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
    searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
    return true;
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    int id = item.getItemId();
    if (id == R.id.action_weather_config) {
      startActivity(new Intent(this, WeatherConfigListActivity.class));
    }
    return true;
  }

  @Override public void inject() {
    DaggerMainComponent.builder()
        .appComponent(((App) getApplication()).getAppComponent())
        .mainModule(new MainModule(getSupportFragmentManager()))
        .locationModule(new LocationModule(this))
        .build()
        .inject(this);
  }
}
