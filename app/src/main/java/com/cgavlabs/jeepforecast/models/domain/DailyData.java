package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmObject;

public class DailyData extends RealmObject {

  private Long time;
  private String summary;
  private String icon;
  private Long sunriseTime;
  private Long sunsetTime;
  private Double moonPhase;
  private Double precipIntensity;
  private Double precipIntensityMax;
  private Double precipProbability;
  private Double temperatureMin;
  private Long temperatureMinTime;
  private Double temperatureMax;
  private Long temperatureMaxTime;
  private Double apparentTemperatureMin;
  private Long apparentTemperatureMinTime;
  private Double apparentTemperatureMax;
  private Long apparentTemperatureMaxTime;
  private Double dewPoint;
  private Double humidity;
  private Double windSpeed;
  private Long windBearing;
  private Double visibility;
  private Double cloudCover;
  private Double pressure;
  private Double ozone;
  private Long precipIntensityMaxTime;
  private String precipType;

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

  public Long getSunriseTime() {
    return sunriseTime;
  }

  public void setSunriseTime(Long sunriseTime) {
    this.sunriseTime = sunriseTime;
  }

  public Long getSunsetTime() {
    return sunsetTime;
  }

  public void setSunsetTime(Long sunsetTime) {
    this.sunsetTime = sunsetTime;
  }

  public Double getMoonPhase() {
    return moonPhase;
  }

  public void setMoonPhase(Double moonPhase) {
    this.moonPhase = moonPhase;
  }

  public Double getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Double precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Double getPrecipIntensityMax() {
    return precipIntensityMax;
  }

  public void setPrecipIntensityMax(Double precipIntensityMax) {
    this.precipIntensityMax = precipIntensityMax;
  }

  public Double getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Double precipProbability) {
    this.precipProbability = precipProbability;
  }

  public Double getTemperatureMin() {
    return temperatureMin;
  }

  public void setTemperatureMin(Double temperatureMin) {
    this.temperatureMin = temperatureMin;
  }

  public Long getTemperatureMinTime() {
    return temperatureMinTime;
  }

  public void setTemperatureMinTime(Long temperatureMinTime) {
    this.temperatureMinTime = temperatureMinTime;
  }

  public Double getTemperatureMax() {
    return temperatureMax;
  }

  public void setTemperatureMax(Double temperatureMax) {
    this.temperatureMax = temperatureMax;
  }

  public Long getTemperatureMaxTime() {
    return temperatureMaxTime;
  }

  public void setTemperatureMaxTime(Long temperatureMaxTime) {
    this.temperatureMaxTime = temperatureMaxTime;
  }

  public Double getApparentTemperatureMin() {
    return apparentTemperatureMin;
  }

  public void setApparentTemperatureMin(Double apparentTemperatureMin) {
    this.apparentTemperatureMin = apparentTemperatureMin;
  }

  public Long getApparentTemperatureMinTime() {
    return apparentTemperatureMinTime;
  }

  public void setApparentTemperatureMinTime(Long apparentTemperatureMinTime) {
    this.apparentTemperatureMinTime = apparentTemperatureMinTime;
  }

  public Double getApparentTemperatureMax() {
    return apparentTemperatureMax;
  }

  public void setApparentTemperatureMax(Double apparentTemperatureMax) {
    this.apparentTemperatureMax = apparentTemperatureMax;
  }

  public Long getApparentTemperatureMaxTime() {
    return apparentTemperatureMaxTime;
  }

  public void setApparentTemperatureMaxTime(Long apparentTemperatureMaxTime) {
    this.apparentTemperatureMaxTime = apparentTemperatureMaxTime;
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

  public Long getPrecipIntensityMaxTime() {
    return precipIntensityMaxTime;
  }

  public void setPrecipIntensityMaxTime(Long precipIntensityMaxTime) {
    this.precipIntensityMaxTime = precipIntensityMaxTime;
  }

  public String getPrecipType() {
    return precipType;
  }

  public void setPrecipType(String precipType) {
    this.precipType = precipType;
  }
}
