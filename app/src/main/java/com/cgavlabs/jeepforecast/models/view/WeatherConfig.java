package com.cgavlabs.jeepforecast.models.view;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherConfig extends RealmObject {

  @PrimaryKey private String name;
  private String imagePath;
  private Integer highTemp;
  private Integer lowTemp;
  private Integer precipThresh;

  public WeatherConfig() {
  }

  public WeatherConfig(String name, Integer highTemp, Integer lowTemp, Integer precipThresh,
      String imagePath) {
    this.name = name;
    this.highTemp = highTemp;
    this.lowTemp = lowTemp;
    this.precipThresh = precipThresh;
    this.imagePath = imagePath;
  }

  public Integer getHighTemp() {
    return highTemp;
  }

  public void setHighTemp(Integer highTemp) {
    this.highTemp = highTemp;
  }

  public Integer getPrecipThresh() {
    return precipThresh;
  }

  public void setPrecipThresh(Integer precipThresh) {
    this.precipThresh = precipThresh;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
