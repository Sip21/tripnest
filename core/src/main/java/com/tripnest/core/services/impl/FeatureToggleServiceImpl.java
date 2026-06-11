package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.FeatureToggleConfig;
import com.tripnest.core.services.FeatureToggleService;

@Component(service = FeatureToggleService.class, immediate = true)
@Designate(ocd = FeatureToggleConfig.class)
public class FeatureToggleServiceImpl implements FeatureToggleService {

    private static final Logger Log = LoggerFactory.getLogger(FeatureToggleServiceImpl.class);

    private boolean discountFlag;

    @Activate
    @Modified
    protected void activate(FeatureToggleConfig config) {
        Log.info("FeatureToggleServiceImpl Activated :::");
        this.discountFlag = config.discountFlag();
    }

    @Override
    public Boolean getDiscountFlag() {
        return discountFlag;
    }

}
