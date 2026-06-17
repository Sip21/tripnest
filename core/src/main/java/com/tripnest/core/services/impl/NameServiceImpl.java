package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.NameService;

@Component(service = NameService.class, immediate = true)
public class NameServiceImpl implements NameService {

    @Override
    public String getName(String name) {
        return "Welcome " + name;
    }

}
