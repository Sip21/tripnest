package com.tripnest.core.services;

import com.tripnest.core.models.UserSessionPOJO;

public interface SessionService {

    UserSessionPOJO createSession(String username);

    UserSessionPOJO getSession(String sessionId);
}
