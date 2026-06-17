package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.PageSearchService;

@Model(adaptables = Resource.class)
public class PageSearchModel {

    @OSGiService
    private PageSearchService service;

    private List<String> pagePaths;

    @PostConstruct
    protected void init() {
        pagePaths = service.getPagePaths();
    }

    public List<String> getPagePaths() {
        return pagePaths;
    }

}
