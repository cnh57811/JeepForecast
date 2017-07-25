package com.cgavlabs.jeepforecast.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import rx.Observable;
import rx.subjects.PublishSubject;
import timber.log.Timber;

public class RxHelper {

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
}
