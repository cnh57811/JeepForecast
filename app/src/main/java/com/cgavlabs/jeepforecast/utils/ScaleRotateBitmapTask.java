package com.cgavlabs.jeepforecast.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import java.lang.ref.WeakReference;

public class ScaleRotateBitmapTask extends AsyncTask<Integer, Void, Bitmap> {
  private final Activity activity;
  private final String imagePath;
  private final WeakReference<ImageView> imageRef;

  public ScaleRotateBitmapTask(Activity activity, String imagePath, ImageView imageView) {
    this.activity = activity;
    this.imagePath = imagePath;
    this.imageRef = new WeakReference<>(imageView);
  }

  @Override protected Bitmap doInBackground(Integer... maxImgSize) {
    return Utils.getScaledRotatedBitmap(activity, imagePath, maxImgSize[0]);
  }

  @Override protected void onPostExecute(Bitmap bitmap) {
    if (bitmap != null) {
      ImageView iv = imageRef.get();
      if (iv != null) {
        iv.setBackground(null);
        iv.setImageBitmap(bitmap);
      }
    }
  }
}
