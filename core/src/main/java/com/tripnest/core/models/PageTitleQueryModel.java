package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.PageTitleService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageTitleQueryModel {

    @OSGiService
    private PageTitleService service;

    private List<String> pageByTitle;

    @PostConstruct
    protected void init() {
        pageByTitle = service.getPageByTitle();
    }

    public List<String> getPageByTitle() {
        return pageByTitle;
    }

}
