package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.ResourceApiService;

@Model(adaptables = SlingHttpServletRequest.class)
public class ResourceApiModel {
    private static final Logger log = LoggerFactory.getLogger(ResourceApiModel.class);

    @OSGiService
    private ResourceApiService resourceapiservice;

    public int getChildNodes() {
        if (resourceapiservice == null) {
            log.error("ResourceApiService is null");
            return 0;
        }
        return resourceapiservice.getChildNodes("/content/tripnest");
    }

}
