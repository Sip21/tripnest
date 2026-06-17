package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.ModifiedPagesService;

@Model(adaptables = Resource.class)
public class ModifiedPageModel {

    @OSGiService
    private ModifiedPagesService service;

    private List<ModifiedPageInfo> pagePaths;

    @PostConstruct
    protected void init() {
        pagePaths = service.getLatestModifiedPages();
    }

    public List<ModifiedPageInfo> getPagePaths() {
        return pagePaths;
    }

}
