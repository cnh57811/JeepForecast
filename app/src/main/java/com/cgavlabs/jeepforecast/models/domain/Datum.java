package com.cgavlabs.jeepforecast.models.domain;

import java.util.HashMap;
import java.util.Map;

public class Datum {

  private Integer time;
  private Double precipIntensity;
  private Double precipIntensityError;
  private Double precipProbability;
  private String precipType;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public Integer getTime() {
    return time;
  }

  public void setTime(Integer time) {
    this.time = time;
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

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}