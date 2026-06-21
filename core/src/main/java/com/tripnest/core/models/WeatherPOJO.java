package com.tripnest.core.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherPOJO {

    private CurrentPOJO current;

    public CurrentPOJO getCurrent() {
        return current;
    }

    public void setCurrent(CurrentPOJO current) {
        this.current = current;
    }

}
