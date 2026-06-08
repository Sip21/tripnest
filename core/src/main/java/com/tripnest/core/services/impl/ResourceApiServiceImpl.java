package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.models.ResourceApiModel;
import com.tripnest.core.services.ResourceApiService;

@Component(service = ResourceApiService.class, immediate = true)
public class ResourceApiServiceImpl implements ResourceApiService {
    private static final Logger log = LoggerFactory.getLogger(ResourceApiServiceImpl.class);

    // Step 1: Need ResourceResolverFactory called here.
    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    public int getChildNodes(String path) {

        // Step 2: Open resourceresolver
        Map<String, Object> param = new HashMap<>();
        param.put(resourceResolverFactory.SUBSERVICE, "tripnest-subservice");

        // Step 3: We need to have try/catch now to get resolver from
        // resourceResolverFactory
        try (ResourceResolver resolver = resourceResolverFactory.getServiceResourceResolver(param)) {

            // Step 4: Now from resolver we need to have resource.
            Resource resource = resolver.getResource(path);

            // Step 5: Now we loop in so we can get child resource.
            if (resource != null) {
                int count = 0;
                for (Resource child : resource.getChildren()) {
                    count++;
                }
                return count;
            }
        } catch (LoginException e) {
            log.error("LoginException while getting ResourceResolver for path: {}", path, e);
        }
        return 0;
    }

}
