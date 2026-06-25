package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.dam.api.Asset;
import com.tripnest.core.services.AssetService;

@Component(service = AssetService.class, immediate = true)
public class AssetServiceImpl implements AssetService {

    private static final Logger LOG = LoggerFactory.getLogger(AssetServiceImpl.class);

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    public void printAssetDetails(String assetPath) {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {

            Resource resource = resolver.getResource(assetPath);
            if (resource != null) {
                Asset asset = resource.adaptTo(Asset.class);
                if (asset != null) {

                    LOG.info("Asset Name : {}", asset.getName());
                    LOG.info("Asset Path : {}", asset.getPath());
                    LOG.info("Mime Type  : {}", asset.getMimeType());
                }
            }
        } catch (Exception e) {
            LOG.error("Error : {}", e);
        }

    }

}
