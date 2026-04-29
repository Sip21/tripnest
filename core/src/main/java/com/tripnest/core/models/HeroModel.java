package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeroModel {

    @ValueMapValue
    private String heading;

    @ValueMapValue
    private String subheading;

    @ValueMapValue
    private String backgroundImage;

    @ValueMapValue
    private String ctaText;

    @ValueMapValue
    private String ctaLink;

    public String getSubheading() {
        return subheading;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public String getCtaText() {
        return ctaText;
    }

    public String getCtaLink() {
        if (ctaLink != null && ctaLink.startsWith("/content")) {
            return ctaLink + ".html";
        }
        return ctaLink;
    }

    public String getHeading() {
        return heading;
    }
}
