package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Weather extends RealmObject {

  private Double latitude;
  private Double longitude;
  private String timezone;
  private Integer offset;
  private Currently currently;
  private Minutely minutely;
  private Hourly hourly;
  private Daily daily;
  private RealmList<Alert> alerts = null;

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

  public String getTimezone() {
    return timezone;
  }

  public void setTimezone(String timezone) {
    this.timezone = timezone;
  }

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public Currently getCurrently() {
    return currently;
  }

  public void setCurrently(Currently currently) {
    this.currently = currently;
  }

  public Minutely getMinutely() {
    return minutely;
  }

  public void setMinutely(Minutely minutely) {
    this.minutely = minutely;
  }

  public Hourly getHourly() {
    return hourly;
  }

  public void setHourly(Hourly hourly) {
    this.hourly = hourly;
  }

  public Daily getDaily() {
    return daily;
  }

  public void setDaily(Daily daily) {
    this.daily = daily;
  }

  public RealmList<Alert> getAlerts() {
    return alerts;
  }

  public void setAlerts(RealmList<Alert> alerts) {
    this.alerts = alerts;
  }
}