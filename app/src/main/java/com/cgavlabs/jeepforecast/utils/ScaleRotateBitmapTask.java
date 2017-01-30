package com.cgavlabs.jeepforecast.utils;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;
import com.cgavlabs.jeepforecast.utils.Utils;
import java.lang.ref.WeakReference;

public class ScaleRotateBitmapTask extends AsyncTask<Void, Void, Bitmap> {
  private final Activity activity;
  private final String imagePath;
  private final WeakReference<ImageView> imageRef;

  public ScaleRotateBitmapTask(Activity activity, String imagePath, ImageView imageView) {
    this.activity = activity;
    this.imagePath = imagePath;
    this.imageRef = new WeakReference<>(imageView);
  }

  @Override protected Bitmap doInBackground(Void... voids) {
    return Utils.getScaledRotatedBitmap(activity, imagePath);
  }

  @Override protected void onPostExecute(Bitmap bitmap) {
    if (imageRef != null && bitmap != null) {
      ImageView iv = imageRef.get();
      if (iv != null) {
        iv.setBackground(null);
        iv.setImageBitmap(bitmap);
      }
    }
  }
}
