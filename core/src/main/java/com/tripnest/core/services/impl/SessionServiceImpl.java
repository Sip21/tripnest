package com.tripnest.core.services.impl;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.osgi.service.component.annotations.Component;

import com.tripnest.core.models.UserSessionPOJO;
import com.tripnest.core.services.SessionService;

@Component(service = SessionService.class)
public class SessionServiceImpl implements SessionService {

    private final Map<String, UserSessionPOJO> sessions = new ConcurrentHashMap<>();

    @Override
    public UserSessionPOJO createSession(String username) {
        String sessionId = UUID.randomUUID().toString();
        UserSessionPOJO session = new UserSessionPOJO(sessionId, username, LocalDateTime.now());
        sessions.put(sessionId, session);
        return session;
    }

    @Override
    public UserSessionPOJO getSession(String sessionId) {
        return sessions.get(sessionId);
    }

}
