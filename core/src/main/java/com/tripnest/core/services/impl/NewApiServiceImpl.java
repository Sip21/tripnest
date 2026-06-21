package com.tripnest.core.services.impl;

import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.config.NewApiConfig;
import com.tripnest.core.models.NewApiPOJO;
import com.tripnest.core.services.NewApiService;

@Component(service = NewApiService.class, immediate = true)
@Designate(ocd = NewApiConfig.class)
public class NewApiServiceImpl implements NewApiService {

    private static final Logger LOG = LoggerFactory.getLogger(NewApiServiceImpl.class);

    private String apiUrl;
    private boolean enableFlag;

    @Activate
    @Modified
    protected void activate(NewApiConfig config) {
        this.apiUrl = config.apiUrl();
        this.enableFlag = config.enableFlag();
    }

    @Override
    public NewApiPOJO callApi() {
        LOG.info("CALL API METHOD ENTERED");
        LOG.info("API URL {}", apiUrl);

        if (!enableFlag) {
            return null;
        }

        NewApiPOJO user = null;

        try (CloseableHttpClient client = HttpClients.createDefault()) {

            HttpGet request = new HttpGet(apiUrl + "/users/1");
            // HttpGet request = new HttpGet(apiUrl + "/wrongurl");

            try (CloseableHttpResponse response = client.execute(request)) {

                int statusCode = response.getStatusLine().getStatusCode();

                LOG.info("Status Code {}", statusCode);

                if (statusCode == 404) {
                    LOG.error("API Not Found");
                    return null;
                }

                if (statusCode == 500) {
                    LOG.error("Server Error");
                    return null;
                }

                if (statusCode != 200) {
                    LOG.error("Unexpected Status Code {}", statusCode);
                    return null;
                }

                String json = IOUtils.toString(response.getEntity().getContent(), StandardCharsets.UTF_8);

                LOG.info("JSON RESPONSE = {}", json);

                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(json, NewApiPOJO.class);
            }

        } catch (Exception e) {
            LOG.error("API Failed", e);
        }
        return user;
    }
}
