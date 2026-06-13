package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Servlet.class)
@SlingServletResourceTypes(resourceTypes = "tripnest1/components/book", methods = "GET", selectors = "test", extensions = "html")
public class ResourceTypeServlet extends SlingSafeMethodsServlet {

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        // String resourceName = request.getResource().getResourceType();
        // response.setContentType("text/plain");
        // response.getWriter().write("Resource Type Servlet Called");

        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"selector working\"}");
    }

}
