package com.tripnest.core.services;

public interface TokenService {
    String generateToken(String username);

    boolean validateToken(String token);
}
