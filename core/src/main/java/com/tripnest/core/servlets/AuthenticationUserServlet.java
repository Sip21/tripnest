package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AuthenticationUserService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/login",
        "sling.servlet.methods=GET"
})
public class AuthenticationUserServlet extends SlingSafeMethodsServlet {

    @Reference
    AuthenticationUserService service;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean success = service.isValid(username, password);
        if (success) {
            response.getWriter().write("Login Success");
        } else {
            response.getWriter().write("Login Failed");
        }
    }
}