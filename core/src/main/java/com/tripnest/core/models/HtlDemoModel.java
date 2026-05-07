package com.tripnest.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HtlDemoModel {

    @ValueMapValue
    private String title;

    @ValueMapValue
    private String linkPath;

    @ValueMapValue
    private String showFooter;

    @ValueMapValue
    private List<String> items;

    public String getTitle() {
        return title;
    }

    public String getLinkPath() {
        if (linkPath != null && linkPath.startsWith("/content")) {
            return linkPath + ".html";
        }
        return linkPath;
    }

    public List<String> getItems() {
        return items;
    }

    public String getShowFooter() {
        return showFooter;
    }

}
