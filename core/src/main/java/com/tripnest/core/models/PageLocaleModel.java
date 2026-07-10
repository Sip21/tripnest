package com.tripnest.core.models;

import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ScriptVariable;

import com.day.cq.wcm.api.Page;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class PageLocaleModel {

    private static final Logger LOG = LoggerFactory.getLogger(PageLocaleModel.class);

    @ScriptVariable
    private Page currentPage;

    public String getLanguage() {
        LOG.info("Current Page : {}", currentPage);

        if (currentPage == null) {
            return "";
        }

        LOG.info("Current Page Path : {}", currentPage.getPath());

        Locale locale = currentPage.getLanguage();
        if (locale == null) {
            return "";
        }

        LOG.info("Language : {}", locale.getLanguage());
        LOG.info("Country : {}", locale.getCountry());

        return locale.getLanguage();
    }
}
