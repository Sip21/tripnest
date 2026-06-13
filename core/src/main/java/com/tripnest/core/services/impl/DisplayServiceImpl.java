package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.DisplayService;

@Component(service = DisplayService.class, immediate = true)
public class DisplayServiceImpl implements DisplayService {

    @Override
    public String getWelcomeMessage() {
        return "Welcome to TripNest";
    }

}
