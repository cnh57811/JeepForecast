package com.cgavlabs.jeepforecast.models.domain;

import java.util.HashMap;
import java.util.Map;

public class Currently {

  private Double time;
  private String summary;
  private String icon;
  private Double nearestStormDistance;
  private Double precipIntensity;
  private Double precipIntensityError;
  private Double precipProbability;
  private String precipType;
  private Double temperature;
  private Double apparentTemperature;
  private Double dewPoint;
  private Double humidity;
  private Double windSpeed;
  private Double windBearing;
  private Double visibility;
  private Double cloudCover;
  private Double pressure;
  private Double ozone;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public Double getTime() {
    return time;
  }

  public void setTime(Double time) {
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

  public Double getNearestStormDistance() {
    return nearestStormDistance;
  }

  public void setNearestStormDistance(Double nearestStormDistance) {
    this.nearestStormDistance = nearestStormDistance;
  }

  public Double getPrecipIntensity() {
    return precipIntensity;
  }

  public void setPrecipIntensity(Double precipIntensity) {
    this.precipIntensity = precipIntensity;
  }

  public Double getPrecipIntensityError() {
    return precipIntensityError;
  }

  public void setPrecipIntensityError(Double precipIntensityError) {
    this.precipIntensityError = precipIntensityError;
  }

  public Double getPrecipProbability() {
    return precipProbability;
  }

  public void setPrecipProbability(Double precipProbability) {
    this.precipProbability = precipProbability;
  }

  public String getPrecipType() {
    return precipType;
  }

  public void setPrecipType(String precipType) {
    this.precipType = precipType;
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

  public Double getWindBearing() {
    return windBearing;
  }

  public void setWindBearing(Double windBearing) {
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

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}