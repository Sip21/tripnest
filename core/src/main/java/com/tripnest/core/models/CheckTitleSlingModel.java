package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CheckTitleSlingModel {
    private static final Logger LOG = LoggerFactory.getLogger((CheckTitleSlingModel.class));

    @ScriptVariable
    private Page currentPage;

    @PostConstruct
    protected void init() {
        LOG.info("currentPage: {}", currentPage); // check logs first

    }

    public String getTitle() {
        if (currentPage == null)
            return null;
        String title = currentPage.getTitle();
        LOG.info("currentPage Name is :: {}", currentPage.getName());
        return title != null ? title : currentPage.getName();
    }

}
