package com.cgavlabs.jeepforecast.today;

import android.content.Intent;
import android.graphics.Bitmap;
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
import com.cgavlabs.jeepforecast.services.BitmapService;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import rx.Observable;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;

public class TodayFragment extends BaseFragment implements TodayContract.View {

  private static final int SELECT_PICTURE = 1;
  private static final int MAX_IMG_SIZE = 2048;
  @Inject TodayContract.Presenter presenter;
  @Inject BitmapService bitmapSvc;
  @BindView(R.id.temperature_actual) TextView actualTemp;
  @BindView(R.id.temperature_high) TextView highTemp;
  @BindView(R.id.temperature_low) TextView lowTemp;
  @BindView(R.id.day_temp_time) TextView dayTempTime;
  @BindView(R.id.current_temp_time) TextView currentTempTime;
  @BindView(R.id.image) ImageView backgroundImg;

  public TodayFragment() {
  }

  public static TodayFragment newInstance() {
    return new TodayFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_single_day, container, false);
    ButterKnife.bind(this, view);
    return view;
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
        Observable<Bitmap> bitmapObs = presenter.getBackgroundImage(data.getData(), MAX_IMG_SIZE);
        bitmapObs.subscribe(bitmap -> backgroundImg.setImageBitmap(bitmap),
            throwable -> Timber.e(throwable), () -> Timber.d("onCompleted()"));
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

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onWeatherDataUpdated(@SuppressWarnings("UnusedParameters") DataSavedEvent e) {
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
    DaggerTodayComponent.builder()
        .appComponent(((App) getActivity().getApplication()).getAppComponent())
        .todayModule(new TodayModule(this))
        .build()
        .inject(this);
  }
}
