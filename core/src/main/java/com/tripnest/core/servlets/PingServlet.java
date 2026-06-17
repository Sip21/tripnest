package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, immediate = true)
@SlingServletResourceTypes(resourceTypes = "tripnest/components/booklist", methods = "POST", selectors = "details", extensions = "json")
public class PingServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("application/json");
        response.getWriter().write("{\"message\":\"PING !! working\"}");
    }

}
