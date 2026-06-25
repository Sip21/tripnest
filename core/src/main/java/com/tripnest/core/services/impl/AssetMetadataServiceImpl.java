package com.tripnest.core.services.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.tripnest.core.services.AssetMetadataService;

@Component(service = AssetMetadataService.class, immediate = true)
public class AssetMetadataServiceImpl implements AssetMetadataService {

    private static final Logger LOG = LoggerFactory.getLogger(AssetMetadataServiceImpl.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public List<Map<String, String>> getAssetMetadata(String assetPath) {

        // This is only for 1 resource
        // Map<String, String> metadata = new HashMap<>();

        // For All the Assets under the path /content/dam/tripnest
        List<Map<String, String>> result = new ArrayList<>();

        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Resource resource = resolver.getResource(assetPath);

            // This is only for 1 resource
            // if (resource != null) {
            // Asset asset = resource.adaptTo(Asset.class);
            // if (asset != null) {
            // String assetName = asset.getName();
            // String path = asset.getPath();
            // String title = asset.getMetadataValue("dc:title");
            // String description = asset.getMetadataValue("dc:description");
            // metadata.put("assetName", assetName);
            // metadata.put("dc:title", title);
            // metadata.put("dc:description", description);
            // metadata.put("path", path);
            // }
            // }

            // For All the Assets under the path /content/dam/tripnest
            for (Resource child : resource.getChildren()) {
                Asset asset = child.adaptTo(Asset.class);
                if (asset != null) {
                    Map<String, String> metadata = new HashMap<>();

                    metadata.put("assetName", asset.getName());
                    metadata.put("dc:title",
                            asset.getMetadataValue("dc:title"));
                    metadata.put("dc:description",
                            asset.getMetadataValue("dc:description"));
                    metadata.put("path", asset.getPath());
                    result.add(metadata);
                }
            }

        } catch (Exception e) {
            LOG.error("Exception here: {}", e);
        }
        // This is only for 1 resource
        // return metadata;

        // For All the Assets under the path /content/dam/tripnest
        return result;
    }

}
