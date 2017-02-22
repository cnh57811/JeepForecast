package com.cgavlabs.jeepforecast.services;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import java.io.File;
import javax.inject.Inject;
import rx.Single;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BitmapServiceImpl implements BitmapService {

  @Inject Context context;

  public BitmapServiceImpl(Context context) {
    this.context = context;
  }

  @Override public Single<Bitmap> scaleAndRotateBitmap(final String imgPath, final int maxImgSize) {
    return Single.fromCallable(() -> {
      Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
      return getScaledRotatedBitmap(null, imgPath, bitmap, maxImgSize);
    }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
  }

  @Override public Single<Bitmap> scaleAndRotateBitmap(final Uri imgUri, final int maxImgSize) {
    return Single.fromCallable(() -> {
      String imgPath = getImagePath(imgUri);
      Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
      return getScaledRotatedBitmap(imgUri, imgPath, bitmap, maxImgSize);
    }).subscribeOn(Schedulers.computation()).observeOn(AndroidSchedulers.mainThread());
  }

  private Bitmap getScaledRotatedBitmap(Uri selectedImageUri, String imgPath, Bitmap bitmap,
      int maxImgSize) {
    if (bitmap == null) {
      return null;
    }
    int height = (int) (bitmap.getHeight() * ((double) maxImgSize / bitmap.getWidth()));
    bitmap = Bitmap.createScaledBitmap(bitmap, maxImgSize, height, false);
    int rotate = getCameraPhotoOrientation(selectedImageUri, imgPath);
    if (rotate != 0) {
      Matrix m = new Matrix();
      m.setRotate(rotate);
      bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
    }
    return bitmap;
  }

  private int getCameraPhotoOrientation(Uri imageUri, String imagePath) {
    int rotate = 0;
    try {
      if (imageUri != null) {
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
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rotate;
  }

  private String getImagePath(Uri uri) {
    String[] projection = { MediaStore.Images.Media.DATA };
    Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
    if (cursor != null) {
      int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
      cursor.moveToFirst();
      String path = cursor.getString(column_index);
      cursor.close();
      Timber.d("**** image path ****" + path);
      return path;
    }
    // this is our fallback here
    return uri.getPath();
  }
}
