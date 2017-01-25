package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Hourly extends RealmObject {

  private String summary;
  private String icon;
  private RealmList<Data> data = null;

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

  public RealmList<Data> getData() {
    return data;
  }

  public void setData(RealmList<Data> data) {
    this.data = data;
  }
}