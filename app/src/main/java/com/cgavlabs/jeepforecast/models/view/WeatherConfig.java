package com.cgavlabs.jeepforecast.models.view;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class WeatherConfig extends RealmObject {
  @PrimaryKey private String name;
  private String imagePath;

  public WeatherConfig() {
  }

  public WeatherConfig(String name) {
    this.name = name;
  }

  public WeatherConfig(String name, String imagePath) {
    this(name);
    this.imagePath = imagePath;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getImagePath() {
    return imagePath;
  }

  public void setImagePath(String imagePath) {
    this.imagePath = imagePath;
  }
}
