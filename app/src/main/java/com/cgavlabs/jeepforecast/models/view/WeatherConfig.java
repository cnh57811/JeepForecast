package com.cgavlabs.jeepforecast.models.view;

import io.realm.RealmObject;

public class WeatherConfig extends RealmObject {

  private Integer lowTemp;
  private Integer highTemp;
  private Integer lowPrecip;
  private Integer highPrecip;
  private String imagePath;

  public WeatherConfig() {
  }

  public WeatherConfig(Integer lowTemp, Integer highTemp, Integer lowPrecip, Integer highPrecip,
      String imagePath) {
    this.lowTemp = lowTemp;
    this.highTemp = highTemp;
    this.lowPrecip = lowPrecip;
    this.highPrecip = highPrecip;
    this.imagePath = imagePath;
  }

  public Integer getHighTemp() {
    return highTemp;
  }

  public void setHighTemp(Integer highTemp) {
    this.highTemp = highTemp;
  }

  public Integer getHighPrecip() {
    return highPrecip;
  }

  public void setHighPrecip(Integer highPrecip) {
    this.highPrecip = highPrecip;
  }

  public Integer getLowPrecip() {
    return lowPrecip;
  }

  public void setLowPrecip(Integer lowPrecip) {
    this.lowPrecip = lowPrecip;
  }

  public Integer getLowTemp() {
    return lowTemp;
  }

  public void setLowTemp(Integer lowTemp) {
    this.lowTemp = lowTemp;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
