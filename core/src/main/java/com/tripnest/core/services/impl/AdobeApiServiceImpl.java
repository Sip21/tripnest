package com.tripnest.core.services.impl;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.AdobeApiService;
import com.tripnest.core.services.AuthorizationCommonService;
import com.tripnest.core.services.IMSTokenService;

@Component(service = AdobeApiService.class)
public class AdobeApiServiceImpl implements AdobeApiService {

    private static final Logger LOG = LoggerFactory.getLogger(AdobeApiServiceImpl.class);

    private final CloseableHttpClient httpClient = HttpClients.createDefault();

    @Reference
    private IMSTokenService tokenService;

    @Reference
    private AuthorizationCommonService authService;

    @Override
    public String callApi(String endpoint) {
        String token = tokenService.getAccessToken();
        String header = authService.buildHeader(token);

        HttpGet request = new HttpGet(endpoint);
        request.setHeader("Authorization", header);
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            return EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            LOG.error("Error while calling Adobe API", e);
        }
        return null;
    }

}
