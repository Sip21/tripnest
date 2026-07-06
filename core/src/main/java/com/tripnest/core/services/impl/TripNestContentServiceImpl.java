package com.tripnest.core.services.impl;

import java.nio.file.Paths;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TripNestContentService;

@Component(service = TripNestContentService.class)
public class TripNestContentServiceImpl implements TripNestContentService {

    private static final Logger LOG = LoggerFactory.getLogger(TripNestContentServiceImpl.class);
    private static final String ALLOWED_ROOT = "/content/tripnest";

    @Override
    public Resource getContentSafely(ResourceResolver resolver, String requestedPath) {
        // Validate input
        if (StringUtils.isBlank(requestedPath)) {
            return null;
        }
        // Normalize the path
        String normalizedPath = Paths.get(requestedPath).normalize().toString().replace("\\", "/");
        // Security check
        if (!isWithinAllowedRoot(normalizedPath)) {
            LOG.warn("Rejected access outside allowed root.");
            return null;
        }
        // Access repository only after validation
        return resolver.getResource(normalizedPath);
    }

    private boolean isWithinAllowedRoot(String path) {
        return path.equals(ALLOWED_ROOT)
                || path.startsWith(ALLOWED_ROOT + "/");
    }

}
