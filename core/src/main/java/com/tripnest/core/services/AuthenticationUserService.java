package com.tripnest.core.services;

public interface AuthenticationUserService {

    boolean isValid(String username, String password);
}
