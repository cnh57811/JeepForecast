package com.cgavlabs.jeepforecast.models.view;

public class Day {
  private String currentTemp;
  private String currentTempTime;
  private String lowTemp;
  private String lowTempTime;
  private String highTemp;

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
}
