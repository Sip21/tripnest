package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AuthorizationCommonService;

@Component(service = Servlet.class, property = {
        "sling.servlet.methods=GET",
        "sling.servlet.paths=/bin/tripnest/authheader"
})
public class AuthorizationHeaderServlet extends SlingSafeMethodsServlet {

    @Reference
    private AuthorizationCommonService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String token = "eyJxxxxxxxxxxxxxxxxxxxxx";
        String header = service.buildHeader(token);

        response.setContentType("text/plain");
        response.getWriter().write(header);
    }
}
