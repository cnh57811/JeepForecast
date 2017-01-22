package com.cgavlabs.jeepforecast.models.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Flags {

  private List<String> sources = null;
  private List<String> darkskyStations = null;
  private List<String> lampStations = null;
  private List<String> isdStations = null;
  private List<String> madisStations = null;
  private String units;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

  public List<String> getSources() {
    return sources;
  }

  public void setSources(List<String> sources) {
    this.sources = sources;
  }

  public List<String> getDarkskyStations() {
    return darkskyStations;
  }

  public void setDarkskyStations(List<String> darkskyStations) {
    this.darkskyStations = darkskyStations;
  }

  public List<String> getLampStations() {
    return lampStations;
  }

  public void setLampStations(List<String> lampStations) {
    this.lampStations = lampStations;
  }

  public List<String> getIsdStations() {
    return isdStations;
  }

  public void setIsdStations(List<String> isdStations) {
    this.isdStations = isdStations;
  }

  public List<String> getMadisStations() {
    return madisStations;
  }

  public void setMadisStations(List<String> madisStations) {
    this.madisStations = madisStations;
  }

  public String getUnits() {
    return units;
  }

  public void setUnits(String units) {
    this.units = units;
  }

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}