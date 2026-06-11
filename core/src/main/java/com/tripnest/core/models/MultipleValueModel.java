package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.MultipleValueService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MultipleValueModel {

    @OSGiService
    MultipleValueService multipleValueService;

    public String getMultipleValue() {
        return multipleValueService != null ? multipleValueService.getMultipleValue() : "Values Not Found";
    }

}
