package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.CopyrightConfig;
import com.tripnest.core.services.CopyrightService;

@Component(service = CopyrightService.class, immediate = true)
@Designate(ocd = CopyrightConfig.class)
public class CopyrightServiceImpl implements CopyrightService {

    private static final Logger log = LoggerFactory.getLogger(CopyrightServiceImpl.class);

    private String copyrightText;

    @Activate
    @Modified
    protected void activate(CopyrightConfig config) {
        this.copyrightText = config.copyrightText();
    }

    @Override
    public String getFooterText() {
        log.info("Fetching copyright text for footer: {}", copyrightText);
        return "Copyright 2026 " + copyrightText;
    }

}
