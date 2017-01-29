package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;

public class WeatherConfigListAdapter extends RecyclerView.Adapter {

  private final List<WeatherConfig> weatherConfigs;

  public WeatherConfigListAdapter(List<WeatherConfig> weatherConfigs) {
    this.weatherConfigs = weatherConfigs;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.weather_config_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override public int getItemCount() {
    return weatherConfigs.size();
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {

    private final LinearLayout hidden;

    ViewHolder(final View itemView) {
      super(itemView);
      hidden = (LinearLayout) itemView.findViewById(R.id.ll_can_be_hidden);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          hidden.setVisibility(hidden.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
      });
    }
  }
}
