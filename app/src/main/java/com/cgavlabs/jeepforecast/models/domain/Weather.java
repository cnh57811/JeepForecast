package com.cgavlabs.jeepforecast.models.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Weather {

  private Double latitude;
  private Double longitude;
  private String timezone;
  private Integer offset;
  private Currently currently;
  private Minutely minutely;
  private Hourly hourly;
  private Daily daily;
  private List<Alert> alerts = null;
  private Flags flags;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

  public List<Alert> getAlerts() {
    return alerts;
  }

  public void setAlerts(List<Alert> alerts) {
    this.alerts = alerts;
  }

  public Flags getFlags() {
    return flags;
  }

  public void setFlags(Flags flags) {
    this.flags = flags;
  }

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}