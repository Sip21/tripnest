package com.tripnest.core.services.impl;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.models.UserSessionPOJO;
import com.tripnest.core.services.AuthenticationUserService;
import com.tripnest.core.services.SessionService;
import com.tripnest.core.services.UserService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/sessionlogin",
        "sling.servlet.methods=GET"
})
public class LoginServlet extends SlingSafeMethodsServlet {
    @Reference
    private AuthenticationUserService service;

    @Reference
    private SessionService sessionService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        if (service.isValid(username, password)) {
            UserSessionPOJO session = sessionService.createSession(username);
            response.getWriter().write("Login Success\nSession ID : " + session.getSessionId());
        } else {
            response.getWriter().write("Login Failed");
        }
    }
}