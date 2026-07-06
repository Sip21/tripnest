package com.tripnest.core.servlets;

import java.io.IOException;

import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/checkpermission",
        "sling.servlet.methods=GET"
})
public class CheckPermissionServlet extends SlingSafeMethodsServlet {

    private static final String PATH_TO_CHECK = "/content/tripnest";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        ResourceResolver resolver = request.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);
        response.setContentType("text/plain");

        if (session == null) {
            response.getWriter().write("Denied (no session available)");
            return;
        }

        boolean canRead;
        try {
            // JCR-native permission check — delegates to the repository's ACL engine
            canRead = session.hasPermission(PATH_TO_CHECK, "read");
        } catch (Exception e) {
            // hasPermission can throw RepositoryException in edge cases (e.g. path issues)
            canRead = false;
        }
        response.getWriter().write(canRead ? "Allowed" : "Denied");
    }
}
