package com.cgavlabs.jeepforecast.models.view;

public class Day {
  private Double latitude;
  private Double longitude;
  private String currentTemp;
  private String currentTempTime;
  private String lowTemp;
  private String lowTempTime;
  private String highTemp;
  private String imageUri;

  public Double getLatitude() {
    return latitude;
  }

  public void setLatitude(Double latitude) {
    this.latitude = latitude;
  }

  public Double getLongitude() {
    return longitude;
  }

  public void setLongitude(Double longitude) {
    this.longitude = longitude;
  }

  public String getCurrentTemp() {
    return currentTemp;
  }

  public void setCurrentTemp(String currentTemp) {
    this.currentTemp = currentTemp;
  }

  public String getLowTemp() {
    return lowTemp;
  }

  public void setLowTemp(String lowTemp) {
    this.lowTemp = lowTemp;
  }

  public String getHighTemp() {
    return highTemp;
  }

  public void setHighTemp(String highTemp) {
    this.highTemp = highTemp;
  }

  public String getCurrentTempTime() {
    return currentTempTime;
  }

  public void setCurrentTempTime(String currentTempTime) {
    this.currentTempTime = currentTempTime;
  }

  public String getLowTempTime() {
    return lowTempTime;
  }

  public void setLowTempTime(String lowTempTime) {
    this.lowTempTime = lowTempTime;
  }

  public String getImageUri() {
    return imageUri;
  }

  public void setImageUri(String imageUri) {
    this.imageUri = imageUri;
  }
}
