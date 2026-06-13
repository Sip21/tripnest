package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageTitleModel {

    @SlingObject
    private SlingHttpServletRequest request;

    private String pageTitle;

    @PostConstruct
    protected void init() {
        PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        if (pageManager != null) {
            Page page = pageManager.getPage("/content/tripnest/us/en");
            if (page != null) {
                pageTitle = page.getPageTitle();
            }
        }
    }

    public String getPageTitle() {
        return pageTitle != null ? pageTitle : "Title Not Found";
    }

}
