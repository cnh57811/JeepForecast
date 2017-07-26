package com.cgavlabs.jeepforecast.utils;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.cgavlabs.jeepforecast.models.view.WeatherConfig;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class RxHelper {

    private static final String LOW_IS_ABOVE_HIGH = "Low temp must be lower than the high temp";
    private static final String TEMPS_OVERLAP = "This temp range overlaps another weather config";

    @NonNull
    public static Observable<String> getTextWatcherObservable(EditText editText) {
        final PublishSubject<String> subject = PublishSubject.create();
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("beforeTextChanged " + charSequence.toString());
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Timber.d("onTextChanged " + charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Timber.d("afterTextChanged " + editable.toString());
                subject.onNext(editable.toString());
            }
        });
        return subject;
    }

    @NonNull
    public static Observable<Integer> getTextWatcherObservable(EditText lowTempET, Integer dfault, int first) {
        return getTextWatcherObservable(lowTempET, dfault)
                .mergeWith(Observable.just(first));
    }

    @NonNull
    public static Observable<Integer> getTextWatcherObservable(EditText lowTempET, Integer dfault) {
        return getTextWatcherObservable(lowTempET)
                .debounce(500, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .map(s -> Utils.getIntegerOrDefault(s, dfault));
    }

    @NonNull
    public static Observable<Boolean> getLowHighObservable(Observable<Integer> lowTempObservable,
                                                           Observable<Integer> highTempObservable) {
        return Observable.combineLatest(lowTempObservable, highTempObservable,
                (lt, ht) -> {
                    return lt < ht;
                });
    }

    @NonNull
    public static Observable<Boolean> getOverlapObservable(List<WeatherConfig> allOtherWCS,
                                                           Observable<Integer> lowTempObservable,
                                                           Observable<Integer> highTempObservable) {
        return Observable.combineLatest(lowTempObservable, highTempObservable, (lt, ht) -> {
            for (WeatherConfig wc : allOtherWCS) {
                boolean lowTempOverlaps = lt > wc.getLowTemp() && lt < wc.getHighTemp();
                boolean highTempOverlaps = ht > wc.getLowTemp() && ht < wc.getHighTemp();
                if (lowTempOverlaps || highTempOverlaps) {
                    return true;
                }
            }
            return false;
        });
    }

    public static void getWeatherConfigErrorObservable(Observable<Boolean> lowIsLowerThanHighObservable,
                                                       Observable<Boolean> tempsOverlapObservable,
                                                       AlertDialog dialog,
                                                       EditText lowTempET,
                                                       EditText highTempET) {
        Observable.combineLatest(lowIsLowerThanHighObservable, tempsOverlapObservable,
                (lowIsLowerThanHigh, tempsOverlap) -> {
                    String error = null;
                    if (!lowIsLowerThanHigh) {
                        error = LOW_IS_ABOVE_HIGH;
                    } else if (tempsOverlap) {
                        error = TEMPS_OVERLAP;
                    }
                    return error;
                }).subscribe(error -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(error == null);
            lowTempET.setError(error);
            highTempET.setError(error);
        }, throwable -> Timber.e(throwable, "Uh oh"));
    }
}
