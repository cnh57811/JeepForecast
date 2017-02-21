package com.cgavlabs.jeepforecast.models.domain;

public class Location {
  private String cityState;
  private Double latitude;
  private Double longitude;

  public Location(String cityState, Double latitude, Double longitude) {
    this.cityState = cityState;
    this.latitude = latitude;
    this.longitude = longitude;
  }

  public String getCityState() {
    return cityState;
  }

  public Double getLatitude() {
    return latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  @Override public String toString() {
    return cityState;
  }
}
