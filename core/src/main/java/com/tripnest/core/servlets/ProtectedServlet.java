package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/protectedpath",
        "sling.servlet.methods=GET"
})
public class ProtectedServlet extends SlingSafeMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        ResourceResolver resolver = request.getResourceResolver();
        String id = resolver.getUserID();
        response.setContentType("text/plain");
        response.getWriter().write("admin".equals(id) ? "Welcome Admin" : "Access Denied");
    }
}
