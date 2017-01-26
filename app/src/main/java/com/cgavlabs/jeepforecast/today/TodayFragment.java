package com.cgavlabs.jeepforecast.today;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cgavlabs.jeepforecast.BaseFragment;
import com.cgavlabs.jeepforecast.Contract;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.view.Day;
import javax.inject.Inject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class TodayFragment extends BaseFragment implements Contract.Today.View {

  @Inject Contract.Today.Presenter presenter;
  private TextView actualTemp;
  private TextView highTemp;
  private TextView lowTemp;

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
    return view;
  }

  @Override public void onStart() {
    super.onStart();
    Log.d("TodayFragment", "onStart:");
    EventBus.getDefault().register(this);
  }

  @Override public void onResume() {
    super.onResume();
    Log.d("TodayFragment", "onResume:");
    presenter.getTodaysWeather();
  }

  @Override public void onStop() {
    super.onStop();
    Log.d("TodayFragment", "onStop:");
    EventBus.getDefault().unregister(this);
  }

  @Subscribe(threadMode = ThreadMode.MAIN)
  public void onWeatherDataUpdated(DataSavedEvent e){
    Log.d("TodayFragment", "onWeatherDataUpdated: ");
    presenter.getTodaysWeather();
  }

  @Override public void updateTodaysWeather(Day day) {
    actualTemp.setText(day.getActualTemp());
    highTemp.setText(day.getHighTemp());
    lowTemp.setText(day.getLowTemp());
    Log.d("TodayFragment", "updateTodaysWeather: updated actual temp field");
  }

  @Override public void inject() {
    DaggerTodayComponent.builder().todayModule(new TodayModule(this)).build().inject(this);
  }
}
