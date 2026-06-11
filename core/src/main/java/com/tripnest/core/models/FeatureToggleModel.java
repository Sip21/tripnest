package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.FeatureToggleService;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FeatureToggleModel {

    @OSGiService
    FeatureToggleService featureToggleService;

    public Boolean getDiscountFlag() {
        return featureToggleService != null ? featureToggleService.getDiscountFlag() : false;
    }
}
