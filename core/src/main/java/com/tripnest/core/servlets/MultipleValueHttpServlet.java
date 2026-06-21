package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.MultipleValueHttpService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/togetdetails",
        "sling.servlet.methods=GET"
})
public class MultipleValueHttpServlet extends SlingSafeMethodsServlet {

    @Reference
    private MultipleValueHttpService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.getWriter().write(service.getDetails());
    }
}
