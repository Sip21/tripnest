//This class is sort of utility class, where you are creating the header for Authorization Bearer Token
package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.AuthorizationCommonService;

@Component(service = AuthorizationCommonService.class)
public class AuthorizationCommonServiceImpl implements AuthorizationCommonService {

    private static final String BEARER = "Bearer ";

    @Override
    public String buildHeader(String accessToken) {
        if (accessToken == null || accessToken.isBlank()) {
            throw new IllegalArgumentException("Access token cannot be null or empty.");
        }
        return BEARER + accessToken;
    }

}
