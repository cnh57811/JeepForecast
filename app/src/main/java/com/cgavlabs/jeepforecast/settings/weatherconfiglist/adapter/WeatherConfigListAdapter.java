package com.cgavlabs.jeepforecast.settings.weatherconfiglist.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import timber.log.Timber;

public class WeatherConfigListAdapter extends RecyclerView.Adapter {

  private static final int MAX_IMG_SIZE = 256;
  private List<WeatherConfig> weatherConfigs = new ArrayList<>();
  private WeatherConfigListAdapterContract.Presenter presenter;

  @Inject public WeatherConfigListAdapter(WeatherConfigListAdapterContract.Presenter presenter) {
    this.presenter = presenter;
  }

  public void setWeatherConfigs(List<WeatherConfig> newWeatherConfigs) {
    weatherConfigs.clear();
    weatherConfigs.addAll(newWeatherConfigs);
    notifyDataSetChanged();
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.weather_config_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ViewHolder configHolder = (ViewHolder) holder;
    configHolder.name.setText(weatherConfigs.get(position).getName());
    String imagePath = weatherConfigs.get(position).getImagePath();
    presenter.getThumbnailImage(imagePath, MAX_IMG_SIZE)
        .subscribe(bitmap -> configHolder.image.setImageBitmap(bitmap),
            throwable -> Timber.e(throwable));
  }

  @Override public int getItemCount() {
    return weatherConfigs.size();
  }

  static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @BindView(R.id.ll_can_be_hidden) LinearLayout hidden;
    @BindView(R.id.tv_not_hidden_1) TextView name;
    @BindView(R.id.weather_config_list_item_image) ImageView image;

    ViewHolder(final View itemView) {
      super(itemView);
      ButterKnife.bind(this, itemView);
      itemView.setOnClickListener(this);
    }

    @Override public void onClick(View view) {
      hidden.setVisibility(hidden.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
    }
  }
}
