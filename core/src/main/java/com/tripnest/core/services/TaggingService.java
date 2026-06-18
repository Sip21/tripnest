package com.tripnest.core.services;

import org.apache.sling.api.resource.ResourceResolver;

import com.day.cq.tagging.Tag;

public interface TaggingService {

    Tag getTag(ResourceResolver resolver, String tagId);
}
