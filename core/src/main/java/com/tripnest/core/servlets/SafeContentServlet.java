package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.TripNestContentService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/safecontent",
        "sling.servlet.methods=GET"
})
public class SafeContentServlet extends SlingSafeMethodsServlet {

    @Reference
    private transient TripNestContentService contentService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        String requestedPath = request.getParameter("path");
        ResourceResolver resolver = request.getResourceResolver();

        // Servlet does NOT decide security — it just forwards to the Service and trusts
        // its verdict
        Resource resource = contentService.getContentSafely(resolver, requestedPath);

        if (resource == null) {
            response.setStatus(403);
            response.getWriter().write("Access Denied: path outside allowed scope");
        } else {
            response.getWriter().write("Access Granted: " + resource.getPath());
        }

    }
}
