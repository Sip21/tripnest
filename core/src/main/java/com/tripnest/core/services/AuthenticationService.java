package com.tripnest.core.services;

public interface AuthenticationService {
    void sendOtp(String mobileNumber);

    boolean verifyOtp(String mobileNumber, String otp);

}
