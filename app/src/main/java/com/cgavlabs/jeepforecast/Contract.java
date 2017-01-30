package com.cgavlabs.jeepforecast;

import android.support.v4.util.Pair;
import com.cgavlabs.jeepforecast.models.domain.Currently;
import com.cgavlabs.jeepforecast.models.domain.DailyData;
import com.cgavlabs.jeepforecast.models.view.Day;
import com.cgavlabs.jeepforecast.models.view.WeatherConfig;
import java.util.List;

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
    interface View {
      void updateTodaysWeather(Day day);
    }

    interface Presenter {
      void getTodaysWeather();
    }

    interface Interactor {
      Pair<DailyData, Currently> getTodaysWeather();
    }
  }

  interface Config {
    interface View {
      List<WeatherConfig> getWeatherConfigs();
    }

    interface Presenter {
      List<WeatherConfig> getWeatherConfigs();
    }

    interface Interactor {
      List<WeatherConfig> getWeatherConfigs();
    }
  }
}
