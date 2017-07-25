package com.cgavlabs.jeepforecast.models.domain;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Daily extends RealmObject {

    @PrimaryKey
    private int id = 1;
    private String summary;
    private String icon;
    private RealmList<DailyData> data = null;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public RealmList<DailyData> getData() {
        return data;
    }

    public void setData(RealmList<DailyData> data) {
        this.data = data;
    }
}
