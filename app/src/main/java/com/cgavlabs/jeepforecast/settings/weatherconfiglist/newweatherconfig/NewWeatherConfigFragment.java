package com.cgavlabs.jeepforecast.settings.weatherconfiglist.newweatherconfig;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;

import com.bumptech.glide.Glide;
import com.cgavlabs.jeepforecast.App;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import timber.log.Timber;

import static android.app.Activity.RESULT_OK;
import static com.cgavlabs.jeepforecast.Constants.SELECT_PICTURE;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getLowHighObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getOverlapObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getTextWatcherObservable;
import static com.cgavlabs.jeepforecast.utils.RxHelper.getWeatherConfigErrorObservable;
import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

public class NewWeatherConfigFragment extends BaseWeatherConfigFragment {

    @Inject
    NewWeatherConfigContract.Presenter presenter;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        super.onCreateDialog(savedInstanceState);

        List<WeatherConfig> allWCs = presenter.getWeatherConfigs();
        Observable<Integer> lowTempObservable = getTextWatcherObservable(lowTempET, MAX_VALUE);
        Observable<Integer> highTempObservable = getTextWatcherObservable(highTempET, MIN_VALUE);
        Observable<Boolean> lowIsLowerThanHighObservable = getLowHighObservable(lowTempObservable, highTempObservable);
        Observable<Boolean> tempsOverlapObservable = getOverlapObservable(allWCs, lowTempObservable, highTempObservable);

        AlertDialog dialog = buildDialog(null);

        getWeatherConfigErrorObservable(lowIsLowerThanHighObservable, tempsOverlapObservable, dialog, lowTempET, highTempET);

        return dialog;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == SELECT_PICTURE) {
                Timber.d("image selected " + data.getData());
                jeepImg.setBackground(null);
                Glide.with(this).load(data.getData()).into(jeepImg);
                jeepImg.setTag(data.getData().toString());
            }
        }
    }

    @Override
    protected void inject() {
        DaggerNewWeatherConfigComponent.builder()
                .appComponent(((App) getActivity().getApplication()).getAppComponent())
                .build()
                .inject(this);
    }

    public interface NewWeatherConfigDialogListener {
        void onAddNewWeatherConfig(WeatherConfig wc);
    }
}
