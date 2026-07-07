package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AuthenticationService;
import com.tripnest.core.services.OtpProviderService;

@Component(service = AuthenticationService.class)
public class AuthenticationServiceImpl implements AuthenticationService {

    @Reference
    private OtpProviderService otpProviderService;

    @Override
    public void sendOtp(String mobileNumber) {
        otpProviderService.sendOtp(mobileNumber);
    }

    @Override
    public boolean verifyOtp(String mobileNumber, String otp) {
        return otpProviderService.verifyOtp(mobileNumber, otp);
    }
}
