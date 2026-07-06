package com.tripnest.core.services;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

public interface TripNestContentService {
    Resource getContentSafely(ResourceResolver resolver, String requestedPath);
}
