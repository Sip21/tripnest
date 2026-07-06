package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.services.AuthenticationUserService;

@Component(service = AuthenticationUserService.class)
public class AuthenticationUserServiceImpl implements AuthenticationUserService {

    @Override
    public boolean isValid(String username, String password) {
        return "admin".equals(username) && "admin".equals(password);
    }

}
