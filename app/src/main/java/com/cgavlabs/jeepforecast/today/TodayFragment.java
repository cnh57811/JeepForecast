package com.cgavlabs.jeepforecast.today;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseFragment;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.events.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.view.Day;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;
import java.io.IOException;
import java.util.List;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

public class TodayFragment extends BaseFragment implements TodayContract.View {

  private static final int SELECT_PICTURE = 1;
  private static final int MAX_IMG_SIZE = 2048;
  @Inject TodayContract.Presenter presenter;
  @Inject SharedPrefs sharedPrefs;
  @BindView(R.id.degree_type) TextView degreeType;
  @BindView(R.id.temperature_actual) TextView actualTemp;
  @BindView(R.id.temperature_high) TextView highTemp;
  @BindView(R.id.temperature_low) TextView lowTemp;
  @BindView(R.id.day_temp_time) TextView dayTempTime;
  @BindView(R.id.current_temp_time) TextView currentTempTime;
  @BindView(R.id.image) ImageView backgroundImg;
  @BindView(R.id.location) TextView location;

  public TodayFragment() {
  }

  public static TodayFragment newInstance() {
    return new TodayFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    Timber.d("onCreateView()");
    View view = inflater.inflate(R.layout.fragment_single_day, container, false);
    ButterKnife.bind(this, view);
    return view;
  }

  @Override public void onStart() {
    Timber.d("onStart()");
    super.onStart();
    EventBus.getDefault().register(this);
  }

  @Override public void onResume() {
    Timber.d("onResume()");
    super.onResume();
    presenter.getTodaysWeather(sharedPrefs.getLatitude(), sharedPrefs.getLongitude());
  }

  @Override public void onStop() {
    super.onStop();
    EventBus.getDefault().unregister(this);
  }

  @OnClick(R.id.btn_choose_photo) public void getPhoto() {
    Intent intent = new Intent();
    intent.setType("image/*");
    intent.setAction(Intent.ACTION_GET_CONTENT);
    startActivityForResult(Intent.createChooser(intent, "Select Picture"), SELECT_PICTURE);
  }

  @Override public void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (resultCode == RESULT_OK) {
      if (requestCode == SELECT_PICTURE) {
        presenter.getBackgroundImage(data.getData(), MAX_IMG_SIZE)
            .subscribe(bitmap -> backgroundImg.setImageBitmap(bitmap),
                throwable -> Timber.e(throwable));
      }
    }
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onWeatherDataUpdated(@SuppressWarnings("UnusedParameters") DataSavedEvent e) {
    Timber.d("Event bus initiated weather update");
    presenter.getTodaysWeather(sharedPrefs.getLatitude(), sharedPrefs.getLongitude());
  }

  @Override public void updateTodaysWeather(Day day) {
    Geocoder gc = new Geocoder(getContext());
    if (day.getLatitude() != null && day.getLongitude() != null) {
      List<Address> addresses = null;
      try {
        addresses = gc.getFromLocation(day.getLatitude(), day.getLongitude(), 1);
      } catch (IOException e) {
        e.printStackTrace();
      }
      if (addresses.size() > 0) {
        Timber.d("Address on view: " + addresses.get(0).getAddressLine(1));
        location.setText(addresses.get(0).getAddressLine(1));
      }
    } else {
      Timber.d("Latitude or Longitude was null can't update the view");
    }
    degreeType.setVisibility(View.VISIBLE);
    actualTemp.setText(day.getCurrentTemp());
    highTemp.setText(day.getHighTemp());
    lowTemp.setText(day.getLowTemp());
    dayTempTime.setText(day.getLowTempTime());
    currentTempTime.setText(day.getCurrentTempTime());
  }

  @Override public void inject() {
    DaggerTodayComponent.builder()
        .appComponent(((App) getActivity().getApplication()).getAppComponent())
        .todayModule(new TodayModule(this))
        .build()
        .inject(this);
  }
}
