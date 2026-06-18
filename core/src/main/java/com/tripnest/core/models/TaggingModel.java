package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.tagging.Tag;
import com.tripnest.core.services.TaggingService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class TaggingModel {

    private static final String TAG_ID = "tripnest:travel";

    @SlingObject
    private ResourceResolver resourceResolver;

    @OSGiService
    private TaggingService taggingService;

    private String title;
    private String path;

    @PostConstruct
    protected void init() {
        Tag tag = taggingService.getTag(resourceResolver, TAG_ID);
        if (tag != null) {
            title = tag.getTitle();
            path = tag.getPath();
        }
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return path;
    }

}
