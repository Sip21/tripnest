package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.SiteConfig;
import com.tripnest.core.services.SiteNameService;

@Component(service = SiteNameService.class, immediate = true)
@Designate(ocd = SiteConfig.class)
public class SiteNameServiceImpl implements SiteNameService {

    private static final Logger LOG = LoggerFactory.getLogger(SiteNameServiceImpl.class);

    private String siteName;

    @Activate
    @Modified
    protected void activate(SiteConfig config) {
        this.siteName = config.siteName();
        LOG.info("SiteNameServiceImpl activated");
        LOG.debug("SiteNameServiceImpl activated. Site Name: {}", siteName);
    }

    public String getWelcomeMessage() {
        LOG.debug("getWelcomeMessage called, siteName = {}", siteName);
        return "Welcome to " + siteName;
    }
}
