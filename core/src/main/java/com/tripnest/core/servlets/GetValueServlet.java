package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.models.GetValuePOJO;
import com.tripnest.core.services.GetValueService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/getvalue",
        "sling.servlet.methods=GET"
})
public class GetValueServlet extends SlingSafeMethodsServlet {

    @Reference
    private GetValueService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        GetValuePOJO todo = service.getDetails();

        response.setContentType("text/plain");

        response.getWriter().write(
                "UserId : " + todo.getUserId()
                        + "\nId : " + todo.getId()
                        + "\nTitle : " + todo.getTitle()
                        + "\nCompleted : " + todo.isCompleted());
    }
}