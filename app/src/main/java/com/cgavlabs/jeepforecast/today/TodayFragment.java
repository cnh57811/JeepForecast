package com.cgavlabs.jeepforecast.today;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.BaseFragment;
import com.cgavlabs.jeepforecast.R;
import com.cgavlabs.jeepforecast.events.DataSavedEvent;
import com.cgavlabs.jeepforecast.models.view.Day;
import com.cgavlabs.jeepforecast.utils.SharedPrefs;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

public class TodayFragment extends BaseFragment implements TodayContract.View {

    @Inject
    TodayContract.Presenter presenter;
    @Inject
    SharedPrefs sharedPrefs;
    @BindString(R.string.degrees_farenheight)
    String degreeType;
    @BindView(R.id.degree_type)
    TextView degreeTypeTv;
    @BindView(R.id.temperature_actual)
    TextView actualTemp;
    @BindView(R.id.temperature_high)
    TextView highTemp;
    @BindView(R.id.temperature_low)
    TextView lowTemp;
    @BindView(R.id.day_temp_time)
    TextView dayTempTime;
    @BindView(R.id.current_temp_time)
    TextView currentTempTime;
    @BindView(R.id.image)
    ImageView backgroundImg;
    @BindView(R.id.location)
    TextView location;

    public TodayFragment() {
    }

    public static TodayFragment newInstance() {
        return new TodayFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Timber.d("onCreateView()");
        View view = inflater.inflate(R.layout.fragment_single_day, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d("onStart()");
        EventBus.getDefault().register(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d("onResume()");
        presenter.getTodaysWeather(sharedPrefs.getLatitude(), sharedPrefs.getLongitude());
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onWeatherDataUpdated(@SuppressWarnings("UnusedParameters") DataSavedEvent e) {
        Timber.d("Event bus initiated weather update");
        presenter.getTodaysWeather(sharedPrefs.getLatitude(), sharedPrefs.getLongitude());
    }

    @Override
    public void updateTodaysWeather(Day day) {
        Geocoder gc = new Geocoder(getContext());
        if (day.getLatitude() != null && day.getLongitude() != null) {
            List<Address> addresses = null;
            try {
                Timber.d("Start gc.getFromLocation()");
                addresses = gc.getFromLocation(day.getLatitude(), day.getLongitude(), 1);
                Timber.d("End gc.getFromLocation()");
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (addresses != null && addresses.size() > 0) {
                Timber.d("Address on view: " + addresses.get(0).getAddressLine(1));
                location.setText(addresses.get(0).getAddressLine(1));
            }
            degreeTypeTv.setText(degreeType);
            actualTemp.setText(day.getCurrentTemp());
            highTemp.setText(day.getHighTemp());
            lowTemp.setText(day.getLowTemp());
            dayTempTime.setText(day.getLowTempTime());
            currentTempTime.setText(day.getCurrentTempTime());
            Glide.with(this)
                    .load(day.getImageUri())
                    .into(backgroundImg);
        } else {
            Timber.d("Latitude or Longitude was null can't update the view");
        }
    }

    @Override
    public void inject() {
        DaggerTodayComponent.builder()
                .appComponent(((App) getActivity().getApplication()).getAppComponent())
                .todayModule(new TodayModule(this))
                .build()
                .inject(this);
    }
}
