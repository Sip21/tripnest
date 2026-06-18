package com.tripnest.core.services;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

public interface TagSearchService {

    List<String> getPages(ResourceResolver resolver, String tagId);
}
