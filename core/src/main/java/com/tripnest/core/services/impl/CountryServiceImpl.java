package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.CountryConfig;
import com.tripnest.core.services.CountryService;

@Component(service = CountryService.class, immediate = true)
@Designate(ocd = CountryConfig.class)
public class CountryServiceImpl implements CountryService {

    private static final Logger LOG = LoggerFactory.getLogger(CountryServiceImpl.class);

    private String siteName;
    private String countryName;

    @Activate
    @Modified
    protected void activate(CountryConfig config) {
        this.siteName = config.siteName();
        this.countryName = config.countryName();
        LOG.info("** CountryServiceImpl activated **");
    }

    @Override
    public String getMessage() {
        LOG.info("** CountryServiceImpl method GetMessage **");
        return siteName + " " + countryName;
    }

}
