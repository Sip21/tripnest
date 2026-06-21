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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.GetValuePOJO;
import com.tripnest.core.services.GetValueService;

@Component(service = GetValueService.class)
public class GetValueServiceImpl implements GetValueService {

    private static final Logger LOG = LoggerFactory.getLogger(GetValueServiceImpl.class);

    private static final String URL = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    public GetValuePOJO getDetails() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            try (CloseableHttpResponse response = client.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(json, GetValuePOJO.class);
            }

        } catch (IOException e) {
            LOG.error("Error while calling API", e);
            return null;
        }
    }
}
