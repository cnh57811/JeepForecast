package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;


import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.Constants;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;

import static com.cgavlabs.jeepforecast.utils.RxHelper.getLowHighObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getOverlapObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getTextWatcherObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getWeatherConfigErrorObservable;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class EditWeatherConfigFragment extends BaseWeatherConfigFragment {

    @Inject
    NewWeatherConfigContract.Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        Bundle paramsToEdit = getArguments();
        int id = paramsToEdit.getInt(Constants.ID);
        int lowTemp = paramsToEdit.getInt(Constants.LOW_TEMP);
        int highTemp = paramsToEdit.getInt(Constants.HIGH_TEMP);
        int lowPrecip = paramsToEdit.getInt(Constants.LOW_PRECIP);
        int highPrecip = paramsToEdit.getInt(Constants.HIGH_PRECIP);

        lowTempET.setText(String.valueOf(lowTemp));
        highTempET.setText(String.valueOf(highTemp));
        lowPrecipET.setText(String.valueOf(lowPrecip));
        highPrecipET.setText(String.valueOf(highPrecip));

        List<WeatherConfig> allWCs = presenter.getWeatherConfigs();
        List<WeatherConfig> allOtherWCS = new ArrayList<>();
        for (WeatherConfig wc : allWCs) {
            if (id != wc.getId()) {
                allOtherWCS.add(wc);
            }
        }

        Observable<Integer> lowTempObservable = getTextWatcherObservable(lowTempET, MAX_VALUE, lowTemp);
        Observable<Integer> highTempObservable = getTextWatcherObservable(highTempET, MIN_VALUE, highTemp);
        Observable<Boolean> lowIsLowerThanHighObservable = getLowHighObservable(lowTempObservable, highTempObservable);
        Observable<Boolean> tempsOverlapObservable = getOverlapObservable(allOtherWCS, lowTempObservable, highTempObservable);

        AlertDialog dialog = buildDialog(id);

        getWeatherConfigErrorObservable(lowIsLowerThanHighObservable, tempsOverlapObservable, dialog, lowTempET, highTempET);

        return dialog;
    }

    @Override
    protected void inject() {
        DaggerNewWeatherConfigComponent.builder()
                .appComponent(((App) getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);
    }
}
