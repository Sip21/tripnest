package com.tripnest.core.services.impl;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.WeatherNewPOJO;
import com.tripnest.core.models.WeatherPOJO;
import com.tripnest.core.services.CompleteWeatherService;

@Component(service = CompleteWeatherService.class)
public class CompleteWeatherServiceImpl implements CompleteWeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(CompleteWeatherServiceImpl.class);

    private WeatherNewPOJO latestWeather;

    @Override
    public WeatherNewPOJO getLatestWeather() {
        return latestWeather;
    }

    @Override
    public void storeLatestWeather(WeatherNewPOJO weather) {
        this.latestWeather = weather;
    }

    @Override
    public WeatherNewPOJO callWeatherApi() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(
                    "https://api.open-meteo.com/v1/forecast?latitude=18.52&longitude=73.85&current=temperature_2m,relative_humidity_2m,wind_speed_10m");

            try (CloseableHttpResponse response = client.execute(request)) {

                String json = IOUtils.toString(
                        response.getEntity().getContent(),
                        StandardCharsets.UTF_8);
                LOG.info("JSON = {}", json);
                ObjectMapper mapper = new ObjectMapper();
                LOG.info("Weather = {}", mapper.readValue(json, WeatherNewPOJO.class));
                return mapper.readValue(json, WeatherNewPOJO.class);
            }

        } catch (Exception e) {
            LOG.error("Weather API Error", e);
        }
        return null;
    }

}
