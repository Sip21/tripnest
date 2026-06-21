package com.tripnest.core.services.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.ApiPOJO;
import com.tripnest.core.services.ApiService;

@Component(service = ApiService.class)
public class ApiServiceImpl implements ApiService {

    private static final Logger LOG = LoggerFactory.getLogger(ApiServiceImpl.class);
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1";

    @Override
    public ApiPOJO getAPiDetail() {

        ApiPOJO api = new ApiPOJO();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);

            try (CloseableHttpResponse response = client.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();

                JsonNode root = mapper.readTree(json);
                api.setCityName(root.get("address").get("city").asText());
            }

        } catch (IOException e) {
            LOG.error("Error while calling API", e);
        }
        return api;
    }

}
