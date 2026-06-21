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
import com.tripnest.core.models.WeatherPOJO;
import com.tripnest.core.services.WeatherService;

@Component(service = WeatherService.class)
public class WeatherServiceImpl implements WeatherService {

    private static final Logger LOG = LoggerFactory.getLogger(WeatherServiceImpl.class);

    private WeatherPOJO lastResponse;
    private long lastFetchTime;

    @Override
    public WeatherPOJO getWeather() {
        long currentTime = System.currentTimeMillis();

        // 5 minutes = 300000 milliseconds
        if (lastResponse != null && (currentTime - lastFetchTime) < 300000) {
            LOG.info("Returning Cached Response");
            return lastResponse;
        }
        LOG.info("Calling Weather API");

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(
                    "https://api.open-meteo.com/v1/forecast?latitude=18.52&longitude=73.85&current=temperature_2m");

            try (CloseableHttpResponse response = client.execute(request)) {
                int statusCode = response.getStatusLine().getStatusCode();

                if (statusCode != 200) {
                    LOG.error("API Failed");
                    return null;
                }
                String json = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);
                ObjectMapper mapper = new ObjectMapper();
                WeatherPOJO weather = mapper.readValue(json, WeatherPOJO.class);

                // Update Cache
                lastResponse = weather;
                lastFetchTime = currentTime;

                LOG.info("Cache Updated");
                return weather;
            }
        } catch (Exception e) {
            LOG.error("Error while calling API", e);
        }
        return null;
    }

}
