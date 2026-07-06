package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/serviceuser",
        "sling.servlet.methods=GET"
})
public class ServiceUserServlet extends SlingSafeMethodsServlet {

    private static final String SUBSERVICE_NAME = "tripnest-subservice";
    private static final String PATH_TO_READ = "/content/tripnest";

    @Reference
    ResourceResolverFactory resolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, SUBSERVICE_NAME);
        response.setContentType("text/plain");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            String id = resolver.getUserID();
            Resource resource = resolver.getResource(PATH_TO_READ);

            response.getWriter().write("Logged in as: " + id + "\n");
            response.getWriter().write(resource != null ? "Content readable: yes" : "Content readable: no");

        } catch (LoginException e) {
            response.getWriter().write("Failed to get service resolver for: " + SUBSERVICE_NAME);
        }

    }

}
