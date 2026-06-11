package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.MultipleValueConfig;
import com.tripnest.core.services.MultipleValueService;

@Component(service = MultipleValueService.class, immediate = true)
@Designate(ocd = MultipleValueConfig.class)
public class MultipleValueServiceImpl implements MultipleValueService {

    private static final Logger Log = LoggerFactory.getLogger(MultipleValueServiceImpl.class);

    private String siteName;
    private String supportEmail;
    private String country;

    @Activate
    @Modified
    protected void activate(MultipleValueConfig config) {
        Log.info("MultipleValueServiceImpl Activated now ::: ");
        this.siteName = config.siteName();
        this.supportEmail = config.supportEmail();
        this.country = config.country();
    }

    @Override
    public String getMultipleValue() {
        Log.info("MultipleValueConfig ::: " + siteName);
        Log.info("MultipleValueConfig ::: " + supportEmail);
        Log.info("MultipleValueConfig ::: " + country);
        return siteName + " | " + supportEmail + " | " + country;
    }

}
