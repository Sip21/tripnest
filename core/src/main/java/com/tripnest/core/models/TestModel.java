package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.TestService;

@Model(adaptables = SlingHttpServletRequest.class)
public class TestModel {

    @OSGiService
    private TestService testService;

    public String getPath() {
        if (testService != null) {
            return testService.getPath("/content/wknd/us");
        }
        return "Error";
    }

}
