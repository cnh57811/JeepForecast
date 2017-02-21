package com.cgavlabs.jeepforecast.utils;

import android.content.SharedPreferences;
import com.cgavlabs.jeepforecast.Constants;
import javax.inject.Inject;

public class SharedPrefs {

  private static final String LATITUDE = "latitude";
  private static final String LONGITUDE = "longitude";
  private static final String USING_CURRENT_LOCATION = "usingCurrentLocation";
  private static final Long DEFAULT_LAT = Double.doubleToRawLongBits(Constants.DEFAULT_LAT);
  private static final Long DEFAULT_LONG = Double.doubleToRawLongBits(Constants.DEFAULT_LONG);
  private final SharedPreferences.Editor editor;
  private final SharedPreferences sharedPreferences;

  @Inject public SharedPrefs(SharedPreferences sharedPreferences) {
    this.sharedPreferences = sharedPreferences;
    this.editor = sharedPreferences.edit();
  }

  public boolean isUsingCurrentLocation() {
    return sharedPreferences.getBoolean(USING_CURRENT_LOCATION, true);
  }

  public void putIsUsingCurrentLocation(boolean isUsingCurrentLocation) {
    editor.putBoolean(USING_CURRENT_LOCATION, isUsingCurrentLocation).apply();
  }

  public Double getLatitude() {
    return Double.longBitsToDouble(sharedPreferences.getLong(LATITUDE, DEFAULT_LAT));
  }

  public Double getLongitude() {
    return Double.longBitsToDouble(sharedPreferences.getLong(LONGITUDE, DEFAULT_LONG));
  }

  public void putLatitude(Double latitude) {
    editor.putLong(LATITUDE, Double.doubleToRawLongBits(latitude)).apply();
  }

  public void putLongitude(Double longitude) {
    editor.putLong(LONGITUDE, Double.doubleToRawLongBits(longitude)).apply();
  }
}
