package com.tripnest.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CheckTitleRModel {
    private static final Logger LOG = LoggerFactory.getLogger(CheckTitleRModel.class);

    @Self
    private Resource resource;

    // private String title;

    public String getTitle() {
        String resourceType = resource.getResourceType();
        LOG.info("ResourceType: {} ", resourceType);

        Resource componentResource = resource.getResourceResolver()
                .getResource("/apps/" + resourceType);
        LOG.info("ComponentResource: {} ", componentResource);

        return componentResource.getValueMap().get("jcr:title", String.class);
    }
}
