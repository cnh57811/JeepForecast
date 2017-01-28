package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MinutelyData extends RealmObject {

  @PrimaryKey
  private Long time;
  private Double precipIntensity;
  private Double precipProbability;

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Double getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Double precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Double getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Double precipProbability) {
    this.precipProbability = precipProbability;
  }
}
