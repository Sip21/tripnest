package com.tripnest.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;

import com.day.cq.i18n.I18n;

import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class I18nModel {

    @Self
    private SlingHttpServletRequest request;

    public String getPlaceOrder() {

        I18n i18n = new I18n(request);
        return i18n.get("Place Order");
    }
}
