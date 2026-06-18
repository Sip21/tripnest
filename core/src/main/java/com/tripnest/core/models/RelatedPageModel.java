package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import com.day.cq.wcm.api.Page;
import com.tripnest.core.services.RelatedPageService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RelatedPageModel {

    private static final String TAG_ID = "tripnest:travel";

    @ScriptVariable
    private Page currentPage;

    @OSGiService
    private RelatedPageService service;

    @SlingObject
    private ResourceResolver resolver;

    private List<String> path;

    @PostConstruct
    protected void init() {
        path = service.getRelatedPages(resolver, currentPage.getPath(), TAG_ID);
    }

    public List<String> getPath() {
        return path;
    }
}
