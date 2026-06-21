package com.tripnest.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherNewPOJO {

    private CompleteWeatherPOJO current;

    public CompleteWeatherPOJO getCurrent() {
        return current;
    }

    public void setCurrent(CompleteWeatherPOJO current) {
        this.current = current;
    }
}
