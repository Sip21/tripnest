package com.tripnest.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/readacl",
        "sling.servlet.methods=GET" })
public class ReadAclServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;
    private static final String PATH_TO_READ = "/content/tripnest";

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/plain");

        ResourceResolver resolver = request.getResourceResolver();
        Session session = resolver.adaptTo(Session.class);

        if (session == null) {
            response.getWriter().write("Error: No session available");
            return;
        }
        try {
            boolean canRead = session.hasPermission(PATH_TO_READ, "read");
            boolean canModify = session.hasPermission(PATH_TO_READ, "add_properties,modifyProperties");

            response.getWriter().write("Current User: " + resolver.getUserID() + "\n");
            response.getWriter().write("Can Read: " + (canRead ? "Yes" : "No") + "\n");
            response.getWriter().write("Can Modify: " + (canModify ? "Yes" : "No") + "\n");

        } catch (RepositoryException e) {
            response.getWriter().write("Error Found");
        }
    }
}
