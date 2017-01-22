package com.cgavlabs.jeepforecast.models.domain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Daily {

  private String summary;
  private String icon;
  private List<Datum__> data = null;
  private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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

  public List<Datum__> getData() {
    return data;
  }

  public void setData(List<Datum__> data) {
    this.data = data;
  }

  public Map<String, Object> getAdditionalProperties() {
    return this.additionalProperties;
  }

  public void setAdditionalProperty(String name, Object value) {
    this.additionalProperties.put(name, value);
  }

}