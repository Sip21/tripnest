package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.CountryService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CountryModel {

    @OSGiService
    private CountryService service;

    public String getMessage() {
        return service != null ? service.getMessage() : "Message Not Found";
    }

}
