package com.tripnest.core.services;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

public interface RelatedPageService {

    List<String> getRelatedPages(ResourceResolver resolver, String currentPagePath, String tagId);
}
