package com.cgavlabs.jeepforecast.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import rx.Observable;
import rx.subjects.PublishSubject;

public class RxHelper {

  public static Observable<String> getTextWatcherObservable(EditText editText) {
    final PublishSubject<String> subject = PublishSubject.create();
    editText.addTextChangedListener(new TextWatcher() {
      @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
      }

      @Override public void afterTextChanged(Editable editable) {
        subject.onNext(editable.toString());
      }
    });
    return subject;
  }
}
