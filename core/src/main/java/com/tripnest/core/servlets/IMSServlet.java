package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.IMSService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/imsconfig",
        "sling.servlet.methods=GET"
})
public class IMSServlet extends SlingSafeMethodsServlet {

    @Reference
    private IMSService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String result = (service != null ? service.getIMSValues() : "IMS Config values didnt found");
        response.getWriter().write(result);
    }
}
