package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.GreetingService;

@Component(service = GreetingService.class, immediate = true)
public class GreetingServiceImpl implements GreetingService {

    @Override
    public String getGreeting() {
        return "Welcome to TripNest";
    }

}
