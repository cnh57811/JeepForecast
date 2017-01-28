package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class MinutelyData extends RealmObject {

  @PrimaryKey
  private Long time;
  private Long precipIntensity;
  private Long precipProbability;

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public Long getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Long precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Long getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Long precipProbability) {
    this.precipProbability = precipProbability;
  }
}
