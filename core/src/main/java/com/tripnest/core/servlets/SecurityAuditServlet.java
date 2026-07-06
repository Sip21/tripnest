package com.tripnest.core.servlets;

import com.tripnest.core.models.AccessReport;
import com.tripnest.core.services.SecurityAuditService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import java.io.IOException;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/securityaudit",
        "sling.servlet.methods=GET"
})
public class SecurityAuditServlet extends SlingSafeMethodsServlet {

    @Reference
    private transient SecurityAuditService auditService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response) throws IOException {

        response.setContentType("text/plain");

        String path = request.getParameter("path");
        ResourceResolver resolver = request.getResourceResolver();

        // Servlet just calls the service and prints the result — no security logic here
        AccessReport report = auditService.checkAccess(resolver, path);

        response.getWriter().write("Current User: " + report.currentUser + "\n");
        response.getWriter().write("User Type: " + report.userType + "\n");
        response.getWriter().write("Can Read: " + (report.canRead ? "Yes" : "No") + "\n");
        response.getWriter().write("Can Modify: " + (report.canModify ? "Yes" : "No") + "\n");
        response.getWriter().write("Can Delete: " + (report.canDelete ? "Yes" : "No") + "\n");
    }
}