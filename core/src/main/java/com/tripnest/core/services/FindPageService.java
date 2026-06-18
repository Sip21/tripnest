package com.tripnest.core.services;

import java.util.List;

import org.apache.sling.api.resource.ResourceResolver;

public interface FindPageService {

    List<String> getFindPage(ResourceResolver resolver, String tagId);
}
