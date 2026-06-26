package com.tripnest.core.services.impl;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.UserNamePOJO;
import com.tripnest.core.services.UsernameService;

@Component(service = UsernameService.class)
public class UsernameServiceImpl implements UsernameService {

    private static final Logger LOG = LoggerFactory.getLogger(UsernameServiceImpl.class);
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1";

    @Override
    public String getUsername() {
        LOG.info("Inside getUsername()");
        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(URL);
            LOG.info("Before execute()");

            try (CloseableHttpResponse response = client.execute(request)) {

                LOG.info("After execute()");

                String json = EntityUtils.toString(response.getEntity());
                LOG.info("JSON = {}", json);

                ObjectMapper mapper = new ObjectMapper();
                UserNamePOJO user = mapper.readValue(json, UserNamePOJO.class);
                LOG.info("Username = {}", user.getUsername());

                return user.getUsername();
            }
        } catch (Exception e) {
            LOG.error("Error calling API", e);
        }
        return null;
    }
}
