package com.tripnest.core.models;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.AuditQueryService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AuditQueryModel {
    private static final Logger LOG = LoggerFactory.getLogger(AuditQueryModel.class);

    @OSGiService
    private AuditQueryService service;

    private List<AuditPOJO> pageDetails;

    @PostConstruct
    protected void init() {
        if (service == null) {
            LOG.error("AuditQueryService is null — OSGi injection failed");
            return;
        }
        pageDetails = service.getRecentlyModifiedPages();
    }

    public List<AuditPOJO> getPageDetails() {
        return pageDetails;
    }

}
