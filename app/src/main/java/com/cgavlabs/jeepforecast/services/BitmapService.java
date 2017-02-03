package com.cgavlabs.jeepforecast.services;

import android.graphics.Bitmap;
import android.net.Uri;
import rx.Single;

public interface BitmapService {
  Single<Bitmap> scaleAndRotateBitmap(String imgPath, int maxImgSize);

  Single<Bitmap> scaleAndRotateBitmap(Uri imgUri, int maxImgSize);
}
