package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Currently extends RealmObject {

  @PrimaryKey private int id = 1;
  private Long time;
  private String summary;
  private String icon;
  private Long nearestStormDistance;
  private Long nearestStormBearing;
  private Long precipIntensity;
  private Long precipProbability;
  private Double temperature;
  private Double apparentTemperature;
  private Double dewPoint;
  private Double humidity;
  private Double windSpeed;
  private Long windBearing;
  private Double visibility;
  private Double cloudCover;
  private Double pressure;
  private Double ozone;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Long getTime() {
    return time;
  }

  public void setTime(Long time) {
    this.time = time;
  }

  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  public Long getNearestStormDistance() {
    return nearestStormDistance;
  }

  public void setNearestStormDistance(Long nearestStormDistance) {
    this.nearestStormDistance = nearestStormDistance;
  }

  public Long getNearestStormBearing() {
    return nearestStormBearing;
  }

  public void setNearestStormBearing(Long nearestStormBearing) {
    this.nearestStormBearing = nearestStormBearing;
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

  public Double getTemperature() {
    return temperature;
  }

  public void setTemperature(Double temperature) {
    this.temperature = temperature;
  }

  public Double getApparentTemperature() {
    return apparentTemperature;
  }

  public void setApparentTemperature(Double apparentTemperature) {
    this.apparentTemperature = apparentTemperature;
  }

  public Double getDewPoint() {
    return dewPoint;
  }

  public void setDewPoint(Double dewPoint) {
    this.dewPoint = dewPoint;
  }

  public Double getHumidity() {
    return humidity;
  }

  public void setHumidity(Double humidity) {
    this.humidity = humidity;
  }

  public Double getWindSpeed() {
    return windSpeed;
  }

  public void setWindSpeed(Double windSpeed) {
    this.windSpeed = windSpeed;
  }

  public Long getWindBearing() {
    return windBearing;
  }

  public void setWindBearing(Long windBearing) {
    this.windBearing = windBearing;
  }

  public Double getVisibility() {
    return visibility;
  }

  public void setVisibility(Double visibility) {
    this.visibility = visibility;
  }

  public Double getCloudCover() {
    return cloudCover;
  }

  public void setCloudCover(Double cloudCover) {
    this.cloudCover = cloudCover;
  }

  public Double getPressure() {
    return pressure;
  }

  public void setPressure(Double pressure) {
    this.pressure = pressure;
  }

  public Double getOzone() {
    return ozone;
  }

  public void setOzone(Double ozone) {
    this.ozone = ozone;
  }
}
