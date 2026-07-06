package com.tripnest.core.models;

import java.time.LocalDateTime;

public class UserSessionPOJO {
    private final String sessionId;
    private final String username;
    private final LocalDateTime loginTime;

    public UserSessionPOJO(String sessionId, String username, LocalDateTime loginTime) {
        this.sessionId = sessionId;
        this.username = username;
        this.loginTime = loginTime;
    }

    public String getSessionId() {
        return sessionId;
    }

    public String getUsername() {
        return username;
    }

    public LocalDateTime getLoginTime() {
        return loginTime;
    }
}
