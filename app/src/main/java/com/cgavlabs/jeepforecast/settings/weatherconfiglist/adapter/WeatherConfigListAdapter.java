package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.bumptech.glide.Glide;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class WeatherConfigListAdapter extends RecyclerView.Adapter {

  private List<WeatherConfig> weatherConfigs = new ArrayList<>();
  private WeatherConfigListAdapterContract.Presenter presenter;
  private Context context;

  @Inject public WeatherConfigListAdapter(WeatherConfigListAdapterContract.Presenter presenter,
      Context context) {
    this.presenter = presenter;
    this.context = context;
  }

  public void setWeatherConfigs(List<WeatherConfig> newWeatherConfigs) {
    weatherConfigs.clear();
    weatherConfigs.addAll(newWeatherConfigs);
    notifyDataSetChanged();
  }

  public void refreshWeatherConfigList() {
    List<WeatherConfig> wcs = presenter.getAllWeatherConfigs();
    setWeatherConfigs(wcs);
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.weather_config_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ViewHolder configHolder = (ViewHolder) holder;
    configHolder.highTemp.setText(String.valueOf(weatherConfigs.get(position).getHighTemp()));
    configHolder.lowTemp.setText(String.valueOf(weatherConfigs.get(position).getLowTemp()));
    configHolder.highPrecip.setText(String.valueOf(weatherConfigs.get(position).getHighPrecip()));
    configHolder.lowPrecip.setText(String.valueOf(weatherConfigs.get(position).getLowPrecip()));
    String imagePath = weatherConfigs.get(position).getImagePath();
    Glide.with(context).load(imagePath).into(configHolder.image);
  }

  @Override public int getItemCount() {
    return weatherConfigs.size();
  }

  public void addWeatherConfig(WeatherConfig weatherConfig) {
    weatherConfigs.add(weatherConfig);
    notifyDataSetChanged();
  }

  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.weather_config_list_item_high_temp) TextView highTemp;
    @BindView(R.id.weather_config_list_item_low_temp) TextView lowTemp;
    @BindView(R.id.weather_config_list_item_high_precip) TextView highPrecip;
    @BindView(R.id.weather_config_list_item_low_precip) TextView lowPrecip;
    @BindView(R.id.weather_config_list_item_image) ImageView image;

    ViewHolder(final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
      //Utils.invertViewVisibility(hidden);
    }
  }
}
