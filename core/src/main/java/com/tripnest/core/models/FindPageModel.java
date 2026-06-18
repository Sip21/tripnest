package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.tripnest.core.services.FindPageService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FindPageModel {

    private static final String TAG_ID = "tripnest:travel";

    @SlingObject
    private ResourceResolver resolver;

    @OSGiService
    private FindPageService service;

    private List<String> pagePaths;

    @PostConstruct
    protected void init() {

        pagePaths = service.getFindPage(resolver, TAG_ID);
    }

    public List<String> getPagePaths() {
        return pagePaths;
    }
}
