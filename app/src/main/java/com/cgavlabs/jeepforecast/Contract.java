package com.cgavlabs.jeepforecast;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.Data;
import com.cgavlabs.jeepforecast.models.view.Day;

public interface Contract {
  interface Main {
    interface Presenter {
      void callWeather(Double latitude, Double longitude);
    }

    interface Interactor {
      void callWeather(Double latitude, Double longitude);
    }
  }

  interface Today {
    interface Presenter {
      Day getTodaysWeather();
    }

    interface Interactor {
      Pair<Data, Currently> getTodaysWeather();
    }
  }
}
