package com.tripnest.core.services;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

public interface ReadPageTagService {

    List<String> getPageTag(ResourceResolver resolver, String pagePath);

}
