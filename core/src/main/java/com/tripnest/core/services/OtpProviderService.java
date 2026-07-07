package com.tripnest.core.services;

public interface OtpProviderService {
    void sendOtp(String mobileNumber);

    boolean verifyOtp(String mobileNumber, String otp);
}
