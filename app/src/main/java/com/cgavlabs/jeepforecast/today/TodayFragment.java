package com.cgavlabs.jeepforecast.today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cgavlabs.jeepforecast.BaseFragment;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.models.domain.Weather;
import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import javax.inject.Inject;

public class TodayFragment extends BaseFragment {

  @Inject Realm realm;

  public TodayFragment() {
  }

  public static TodayFragment newInstance() {
    return new TodayFragment();
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    RealmQuery<Weather> query = realm.where(Weather.class);
    RealmResults<Weather> results = query.findAll();
    String temp = results.first().getCurrently().getTemperature().toString();
    View rootView = inflater.inflate(R.layout.fragment_today, container, false);
    TextView tv = (TextView) rootView.findViewById(R.id.temp_actual_today);
    tv.setText(temp);
    return rootView;
  }

  @Override public void inject() {
    DaggerTodayComponent.create().inject(this);
  }
}
