package com.cgavlabs.jeepforecast.utils;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;
import java.util.Calendar;
import java.util.Date;
import timber.log.Timber;

public class Utils {

  private static final int MAX_IMG_SIZE = 2048;

  public static String roundDouble(Double dbl) {
    return String.valueOf(Math.round(dbl));
  }

  public static Long getStartOfTodayInSeconds() {
    Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, 0);
    c.set(Calendar.MINUTE, 0);
    c.set(Calendar.SECOND, 0);
    c.set(Calendar.MILLISECOND, 0);
    Timber.d(c.getTime().toString());
    return c.getTimeInMillis() / 1000;
  }

  public static Long getEndOfTodayInSeconds() {
    Date date = new Date();
    Calendar c = Calendar.getInstance();
    c.setTime(date);
    c.set(Calendar.HOUR_OF_DAY, c.getActualMaximum(Calendar.HOUR_OF_DAY));
    c.set(Calendar.MINUTE, c.getActualMaximum(Calendar.MINUTE));
    c.set(Calendar.SECOND, c.getActualMaximum(Calendar.SECOND));
    c.set(Calendar.MILLISECOND, c.getActualMaximum(Calendar.MILLISECOND));
    Timber.d(c.getTime().toString());
    return c.getTimeInMillis() / 1000;
  }

  public static Bitmap getScaledRotatedBitmap(Activity activity, Uri selectedImageUri) {
    String imgPath = Utils.getImagePath(activity, selectedImageUri);
    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
    return getScaledRotatedBitmap(activity, selectedImageUri, imgPath, bitmap);
  }

  public static Bitmap getScaledRotatedBitmap(Activity activity, String imgPath) {
    Timber.d("IN getScaledRotatedBitmap");
    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
    Timber.d("Image path decoded");
    Bitmap bmp = getScaledRotatedBitmap(activity, null, imgPath, bitmap);
    Timber.d("OUT getScaledRotatedBitmap");
    return bmp;
  }

  private static Bitmap getScaledRotatedBitmap(Activity activity, Uri selectedImageUri,
      String imgPath, Bitmap bitmap) {
    Timber.d("IN private getScaledRotatedBitmap()");
    int height = (int) (bitmap.getHeight() * (Double.valueOf(MAX_IMG_SIZE) / bitmap.getWidth()));
    bitmap = Bitmap.createScaledBitmap(bitmap, MAX_IMG_SIZE, height, false);
    int rotate = Utils.getCameraPhotoOrientation(activity, selectedImageUri, imgPath);
    if (rotate != 0) {
      Matrix m = new Matrix();
      m.setRotate(rotate);
      bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
    }
    Timber.d("OUT private getScaledRotatedBitmap()");
    return bitmap;
  }

  public static int getCameraPhotoOrientation(Context context, Uri imageUri, String imagePath) {
    int rotate = 0;
    try {
      if(imageUri != null) {
        context.getContentResolver().notifyChange(imageUri, null);
      }
      File imageFile = new File(imagePath);

      ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
      int orientation =
          exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);

      switch (orientation) {
        case ExifInterface.ORIENTATION_ROTATE_270:
          rotate = 270;
          break;
        case ExifInterface.ORIENTATION_ROTATE_180:
          rotate = 180;
          break;
        case ExifInterface.ORIENTATION_ROTATE_90:
          rotate = 90;
          break;
      }

      Timber.i("RotateImage Exif orientation: " + orientation);
      Timber.i("RotateImage Rotate value: " + rotate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rotate;
  }

  public static String getImagePath(Activity activity, Uri uri) {
    // just some safety built in
    if (uri == null) {
      // TODO perform some logging or show user feedback
      return null;
    }
    // try to retrieve the image from the media store first
    // this will only work for images selected from gallery
    String[] projection = { MediaStore.Images.Media.DATA };
    Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
    if (cursor != null) {
      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      String path = cursor.getString(column_index);
      //cursor.close();
      Timber.d("**** image path ****" + path);
      return path;
    }
    // this is our fallback here
    return uri.getPath();
  }
}
