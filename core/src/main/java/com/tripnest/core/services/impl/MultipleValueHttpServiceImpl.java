package com.tripnest.core.services.impl;

import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.services.MultipleValueHttpService;

@Component(service = MultipleValueHttpService.class)
public class MultipleValueHttpServiceImpl implements MultipleValueHttpService {

    private static final Logger LOG = LoggerFactory.getLogger(MultipleValueHttpServiceImpl.class);
    private static final String URL = "https://jsonplaceholder.typicode.com/todos/1";

    @Override
    public String getDetails() {
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            try (CloseableHttpResponse response = client.execute(request)) {
                String json = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                JsonNode root = mapper.readTree(json);
                String title = root.get("title").asText();
                int userId = root.get("userId").asInt();
                int id = root.get("id").asInt();
                boolean completed = root.get("completed").asBoolean();
                return "UserId: " + userId
                        + ", Id: " + id
                        + ", Title: " + title
                        + ", Completed: " + completed;
            }
        } catch (IOException e) {
            LOG.error("Error while calling API", e);
            return "Error";
        }
    }
}
