package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.CopyrightService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CopyrightModel {

    @OSGiService
    private CopyrightService copyrightService;

    public String getFooterText() {
        return copyrightService != null ? copyrightService.getFooterText() : "No Copyright Found!";
    }

}
