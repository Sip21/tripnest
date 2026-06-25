package com.tripnest.core.services.impl;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.AssetManager;
import com.tripnest.core.services.CreateAssetService;

@Component(service = CreateAssetService.class)
public class CreateAssetServiceImpl implements CreateAssetService {

    private static final Logger LOG = LoggerFactory.getLogger(CreateAssetService.class);
    private static final String URL = "https://jsonplaceholder.typicode.com/users/1";

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public String createAssets() {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            AssetManager assetManager = resolver.adaptTo(AssetManager.class);

            // This is to Create Asset
            // if (assetManager == null) {
            // return "AssetManager is not available.";
            // }

            // if (resolver.getResource("/content/dam/tripnest") == null) {
            // return "Folder does not exist.";
            // }
            // String content = "Hello TripNest\nLearn";
            // InputStream inputStream = new
            // ByteArrayInputStream(content.getBytes(StandardCharsets.UTF_8));
            // assetManager.createAsset(
            // "/content/dam/tripnest/report.txt",
            // inputStream,
            // "text/plain",
            // true);
            // resolver.commit();
            // return "Asset created successfully.";

            try (CloseableHttpClient client = HttpClients.createDefault()) {
                HttpGet request = new HttpGet(URL);
                try (CloseableHttpResponse response = client.execute(request)) {
                    String json = EntityUtils.toString(response.getEntity());
                    InputStream is = new ByteArrayInputStream(json.getBytes(StandardCharsets.UTF_8));
                    assetManager.createAsset(
                            "/content/dam/tripnest/post1.json",
                            is,
                            "application/json",
                            true);
                    resolver.commit();
                    return "Asset created successfully from HttpClient";
                }
            }

        } catch (Exception e) {
            LOG.error("Exception while creating asset", e);
            return "Failed to create asset.";
        }
    }

}
