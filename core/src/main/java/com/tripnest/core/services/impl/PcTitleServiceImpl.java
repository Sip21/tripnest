package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.PcTitleService;

@Component(service = PcTitleService.class)
public class PcTitleServiceImpl implements PcTitleService {

    @Override
    public String getWelcomeMessage() {
        return "Welcome to AEM ";
    }
}
