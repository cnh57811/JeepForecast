package com.cgavlabs.jeepforecast.today;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.cgavlabs.jeepforecast.BaseFragment;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.utils.Utils;
import com.cgavlabs.jeepforecast.models.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.view.Day;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

public class TodayFragment extends BaseFragment implements Contract.Today.View {

  private static final int SELECT_PICTURE = 1;
  @Inject Contract.Today.Presenter presenter;
  private TextView actualTemp;
  private TextView highTemp;
  private TextView lowTemp;
  private TextView dayTempTime;
  private TextView currentTempTime;
  private ImageView backgroundImg;

  public TodayFragment() {
  }

  public static TodayFragment newInstance() {
    return new TodayFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_single_day, container, false);
    actualTemp = (TextView) view.findViewById(R.id.temperature_actual);
    highTemp = (TextView) view.findViewById(R.id.temperature_high);
    lowTemp = (TextView) view.findViewById(R.id.temperature_low);
    dayTempTime = (TextView) view.findViewById(R.id.day_temp_time);
    currentTempTime = (TextView) view.findViewById(R.id.current_temp_time);
    backgroundImg = (ImageView) view.findViewById(R.id.image);

    view.findViewById(R.id.btn_choose_photo).setOnClickListener(new View.OnClickListener() {
      public void onClick(View arg0) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
      }
    });

    return view;
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      if (requestCode == SELECT_PICTURE) {
        Bitmap scaledBmp = Utils.getScaledRotatedBitmap(getActivity(), data.getData());
        backgroundImg.setImageBitmap(scaledBmp);
      }
    }
  }

  @Override public void onStart() {
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onResume() {
    super.onResume();
    Timber.d("onResume()");
    presenter.getTodaysWeather();
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN) public void onWeatherDataUpdated(DataSavedEvent e) {
    Timber.d("Event bus initiated weather update");
    presenter.getTodaysWeather();
  }

  @Override public void updateTodaysWeather(Day day) {
    actualTemp.setText(day.getCurrentTemp());
    highTemp.setText(day.getHighTemp());
    lowTemp.setText(day.getLowTemp());
    dayTempTime.setText(day.getLowTempTime());
    currentTempTime.setText(day.getCurrentTempTime());
    Timber.d("UI fields on TODAY screen updated");
  }

  @Override public void inject() {
    DaggerTodayComponent.builder().todayModule(new TodayModule(this)).build().inject(this);
  }
}
