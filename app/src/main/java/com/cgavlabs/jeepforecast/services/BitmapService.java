package com.cgavlabs.jeepforecast.services;

import android.graphics.Bitmap;
import android.net.Uri;
import rx.Observable;

public interface BitmapService {
  Observable<Bitmap> scaleAndRotateBitmap(String imgPath, int maxImgSize);

  Observable<Bitmap> scaleAndRotateBitmap(Uri imgUri, int maxImgSize);
}
