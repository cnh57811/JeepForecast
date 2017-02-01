package com.cgavlabs.jeepforecast.services;

import android.net.Uri;
import android.widget.ImageView;

public interface BitmapService {
  void scaleAndRotateBitmap(String imgPath, int maxImgSize, ImageView imgView);

  void scaleAndRotateBitmap(Uri imgUri, int maxImgSize, ImageView imgView);

  String getImagePath(Uri uri);
}
