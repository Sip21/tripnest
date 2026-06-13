package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/formsubmit",
        "sling.servlet.methods=POST"
})
public class FormSubmitServlet extends SlingAllMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        response.setContentType("application/json");
        if (name != null && !name.isEmpty()) {
            String json = String.format(
                    "{ \"status\": \"success\", \"name\": \"%s\" }",
                    name);
            response.getWriter().write(json);
        } else {
            response.getWriter().write("{ \"status\": \"error\", \"message\": \"name is missing\" }");
        }
    }
}
