package com.tripnest.core.services.impl;

import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;

import com.day.cq.tagging.Tag;
import com.day.cq.tagging.TagManager;
import com.tripnest.core.services.TaggingService;

@Component(service = TaggingService.class)
public class TaggingServiceImpl implements TaggingService {

    @Override
    public Tag getTag(ResourceResolver resolver, String tagId) {
        TagManager tagManager = resolver.adaptTo(TagManager.class);

        if (tagManager != null) {
            return tagManager.resolve(tagId);
        }
        return null;
    }

}
