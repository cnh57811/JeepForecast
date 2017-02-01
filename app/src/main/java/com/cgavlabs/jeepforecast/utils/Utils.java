package com.cgavlabs.jeepforecast.utils;

import java.util.Calendar;
import java.util.Date;
import timber.log.Timber;

public class Utils {

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
}
