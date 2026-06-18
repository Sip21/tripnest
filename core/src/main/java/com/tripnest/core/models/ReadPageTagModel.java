package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.tripnest.core.services.ReadPageTagService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ReadPageTagModel {

    private static final String PAGE_PATH = "/content/tripnest/us/en/destinations";

    @SlingObject
    private ResourceResolver resolver;

    @OSGiService
    private ReadPageTagService service;

    private List<String> tagTitles;

    @PostConstruct
    protected void init() {

        tagTitles = service.getPageTag(resolver, PAGE_PATH);
    }

    public List<String> getTagTitles() {
        return tagTitles;
    }
}
