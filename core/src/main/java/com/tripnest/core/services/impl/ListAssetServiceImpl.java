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
import com.tripnest.core.services.ListAssetService;

@Component(service = ListAssetService.class, immediate = true)
public class ListAssetServiceImpl implements ListAssetService {

    private static final Logger LOG = LoggerFactory.getLogger(ListAssetServiceImpl.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public List<String> getListOfAssets(String assetPath) {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        List<String> assetNames = new ArrayList<>();

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            Resource resource = resolver.getResource(assetPath);
            if (resource != null) {
                for (Resource child : resource.getChildren()) {
                    Asset asset = child.adaptTo(Asset.class);
                    if (asset != null) {
                        LOG.info("Asset Name : {}", asset.getName());
                        assetNames.add(asset.getName());
                    }
                }
            }

        } catch (Exception e) {
            LOG.error("Error : {}", e);
        }
        return assetNames;
    }

}
