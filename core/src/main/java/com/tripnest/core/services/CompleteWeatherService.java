package com.tripnest.core.services;

import com.tripnest.core.models.WeatherNewPOJO;
import com.tripnest.core.models.WeatherPOJO;

public interface CompleteWeatherService {

    WeatherNewPOJO getLatestWeather();

    void storeLatestWeather(WeatherNewPOJO weather);

    WeatherNewPOJO callWeatherApi();
}
