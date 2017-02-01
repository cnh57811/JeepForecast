package com.cgavlabs.jeepforecast.services;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ImageView;
import java.io.File;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;

public class BitmapServiceImpl implements BitmapService {

  @Inject Context context;

  public BitmapServiceImpl(Context context) {
    this.context = context;
  }

  @Override public void scaleAndRotateBitmap(final String imgPath, final int maxImgSize,
      final ImageView imgView) {
    Observable.create(new Observable.OnSubscribe<Bitmap>() {
      @Override public void call(Subscriber<? super Bitmap> subscriber) {
        subscriber.onNext(getScaledRotatedBitmap(imgPath, maxImgSize));
      }
    })
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Bitmap>() {
          @Override public void onCompleted() {
            Timber.d("onCompleted scaleAndRotateBitmap()");
          }

          @Override public void onError(Throwable e) {
            Timber.e(e);
          }

          @Override public void onNext(Bitmap bitmap) {
            imgView.setImageBitmap(bitmap);
          }
        });
  }

  @Override public void scaleAndRotateBitmap(final Uri imgUri, final int maxImgSize,
      final ImageView imgView) {
    Observable.create(new Observable.OnSubscribe<Bitmap>() {
      @Override public void call(Subscriber<? super Bitmap> subscriber) {
        subscriber.onNext(getScaledRotatedBitmap(imgUri, maxImgSize));
      }
    })
        .subscribeOn(Schedulers.computation())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Subscriber<Bitmap>() {
          @Override public void onCompleted() {
            Timber.d("onCompleted scaleAndRotateBitmap()");
          }

          @Override public void onError(Throwable e) {
            Timber.e(e);
          }

          @Override public void onNext(Bitmap bitmap) {
            imgView.setImageBitmap(bitmap);
          }
        });
  }

  public Bitmap getScaledRotatedBitmap(Uri selectedImageUri, int maxImgSize) {
    String imgPath = getImagePath(selectedImageUri);
    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
    return getScaledRotatedBitmap(selectedImageUri, imgPath, bitmap, maxImgSize);
  }

  public Bitmap getScaledRotatedBitmap(String imgPath, int maxImgSize) {
    Timber.d("IN getScaledRotatedBitmap");
    Bitmap bitmap = BitmapFactory.decodeFile(imgPath);
    Timber.d("Image path decoded");
    Bitmap bmp = getScaledRotatedBitmap(null, imgPath, bitmap, maxImgSize);
    Timber.d("OUT getScaledRotatedBitmap");
    return bmp;
  }

  private Bitmap getScaledRotatedBitmap(Uri selectedImageUri, String imgPath, Bitmap bitmap,
      int maxImgSize) {
    Timber.d("IN private getScaledRotatedBitmap()");
    int height = (int) (bitmap.getHeight() * ((double) maxImgSize / bitmap.getWidth()));
    bitmap = Bitmap.createScaledBitmap(bitmap, maxImgSize, height, false);
    int rotate = getCameraPhotoOrientation(selectedImageUri, imgPath);
    if (rotate != 0) {
      Matrix m = new Matrix();
      m.setRotate(rotate);
      bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
    }
    Timber.d("OUT private getScaledRotatedBitmap()");
    return bitmap;
  }

  public int getCameraPhotoOrientation(Uri imageUri, String imagePath) {
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

      Timber.i("RotateImage Exif orientation: " + orientation);
      Timber.i("RotateImage Rotate value: " + rotate);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return rotate;
  }

  public String getImagePath(Uri uri) {
    // just some safety built in
    if (uri == null) {
      // TODO perform some logging or show user feedback
      return null;
    }
    // try to retrieve the image from the media store first
    // this will only work for images selected from gallery
    String[] projection = { MediaStore.Images.Media.DATA };
    //Cursor cursor = activity.managedQuery(uri, projection, null, null, null);
    Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
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
