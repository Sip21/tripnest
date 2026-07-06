package com.tripnest.core.services.impl;

import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.util.Base64;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TokenService;

@Component(service = TokenService.class)
public class TokenServiceImpl implements TokenService {
    private static final Logger LOG = LoggerFactory.getLogger(TokenServiceImpl.class);
    private static final String SECRET = "replace-with-a-long-random-secret-from-config";
    private static final String ALGO = "HmacSHA256";

    @Override
    public String generateToken(String username) {
        // Keeping it for normal testing
        // long expiry = Instant.now().plusSeconds(3600).getEpochSecond();

        // Now changing the expire time for token to 30 sec so it can expire
        long expiry = Instant.now().plusSeconds(30).getEpochSecond();

        String payload = username + "|" + expiry;
        String signature = sign(payload);
        String combined = payload + "|" + signature;
        // LOG.info("Generated Token for user {} : {}", username, token);

        return Base64.getEncoder().encodeToString(combined.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public boolean validateToken(String token) {
        try {
            LOG.info("Received Token: {}", token);
            String decoded = new String(Base64.getDecoder().decode(token), StandardCharsets.UTF_8);
            LOG.info("Decoded Token: {}", decoded);

            String[] parts = decoded.split("\\|");
            LOG.info("Parts Length: {}", parts.length);

            if (parts.length != 3) {
                return false;
            }

            String username = parts[0];
            String expiryStr = parts[1];
            String signature = parts[2];

            String payload = username + "|" + expiryStr;
            String expectedSignature = sign(payload);
            // Reject if signature doesn't match -> token was forged or tampered with
            if (!expectedSignature.equals(signature)) {
                LOG.warn("Token signature mismatch — possible forgery attempt.");
                return false;
            }

            long expiry = Long.parseLong(expiryStr);
            long currentTime = Instant.now().getEpochSecond();
            LOG.info("Current Time : {}", currentTime);
            LOG.info("Expiry Time  : {}", expiry);

            boolean valid = currentTime < expiry;

            LOG.info("Token Expired : {}", !valid);

            return valid;

        } catch (Exception e) {
            LOG.error("Error while validating token.", e);
            return false;
        }
    }

    private String sign(String payload) {
        try {
            Mac mac = Mac.getInstance(ALGO);
            mac.init(new SecretKeySpec(SECRET.getBytes(StandardCharsets.UTF_8), ALGO));
            byte[] hash = mac.doFinal(payload.getBytes(StandardCharsets.UTF_8));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new IllegalStateException("Signing failed", e);
        }
    }

}
