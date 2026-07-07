package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.IMSTokenService;

@Component(service = IMSTokenService.class)
public class IMSTokenServiceImpl implements IMSTokenService {

    @Override
    public String getAccessToken() {
        return "dummy-access-token";
    }

}
