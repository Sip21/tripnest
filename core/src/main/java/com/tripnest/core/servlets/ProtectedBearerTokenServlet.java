package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TokenService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/profile",
        "sling.servlet.methods=GET"
})
public class ProtectedBearerTokenServlet extends SlingSafeMethodsServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProtectedBearerTokenServlet.class);

    @Reference
    private TokenService tokenService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        LOGGER.info("===== ProtectedBearerTokenServlet Invoked =====");

        response.setContentType("text/plain");
        String authHeader = request.getHeader("Authorization");
        LOGGER.info("Authorization Header: {}", authHeader);

        if (authHeader == null ||
                !authHeader.startsWith("Bearer ")) {

            response.setStatus(401);
            response.getWriter().write("Unauthorized");
            return;
        }
        String token = authHeader.substring(7);
        LOGGER.info("Received Token: {}", token);

        if (tokenService.validateToken(token)) {
            LOGGER.info("Token validation successful.");
            response.getWriter().write("Authenticated");
        } else {
            LOGGER.warn("Token validation failed.");
            response.setStatus(401);
            response.getWriter().write("Token Expired or Invalid");
        }
    }
}
