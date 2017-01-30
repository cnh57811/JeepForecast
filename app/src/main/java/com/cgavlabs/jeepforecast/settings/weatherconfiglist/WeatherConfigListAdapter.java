package com.cgavlabs.jeepforecast.settings.weatherconfiglist;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.utils.ScaleRotateBitmapTask;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;
import timber.log.Timber;

public class WeatherConfigListAdapter extends RecyclerView.Adapter {

  private final List<WeatherConfig> weatherConfigs;
  private final Activity activity;

  public WeatherConfigListAdapter(Activity activity, List<WeatherConfig> weatherConfigs) {
    this.weatherConfigs = weatherConfigs;
    this.activity = activity;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.weather_config_list_item, parent, false);
    return new ViewHolder(v);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    ViewHolder configHolder = (ViewHolder) holder;
    configHolder.name.setText(weatherConfigs.get(position).getName());
    //Timber.d("Start getImagePath()");
    String imagePath = weatherConfigs.get(position).getImagePath();
    //Timber.d("end getImagePath()");
    Timber.d("scale and rotate image");
    new ScaleRotateBitmapTask(activity, imagePath, configHolder.image).execute();
    //Bitmap bmp = Utils.getScaledRotatedBitmap(activity, imagePath);
    Timber.d("done ... scale and rotate image");
    //configHolder.image.setImageBitmap(bmp);
  }

  @Override public int getItemCount() {
    return weatherConfigs.size();
  }

  private static class ViewHolder extends RecyclerView.ViewHolder {

    final LinearLayout hidden;
    final TextView name;
    final ImageView image;

    ViewHolder(final View itemView) {
      super(itemView);
      hidden = (LinearLayout) itemView.findViewById(R.id.ll_can_be_hidden);
      name = (TextView) itemView.findViewById(R.id.tv_not_hidden_1);
      image = (ImageView) itemView.findViewById(R.id.weather_config_list_item_image);
      itemView.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          hidden.setVisibility(hidden.getVisibility() == View.VISIBLE ? View.GONE : View.VISIBLE);
        }
      });
    }
  }
}
