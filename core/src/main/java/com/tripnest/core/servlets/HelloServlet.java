package com.tripnest.core.servlets;

import java.io.IOException;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.DisplayService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/hello",
        "sling.servlet.methods=GET"
})
public class HelloServlet extends SlingSafeMethodsServlet {

    @Reference
    private DisplayService displayService;

    protected void doGet(final SlingHttpServletRequest request, final SlingHttpServletResponse response)
            throws IOException {
        String name = request.getParameter("name");
        // response.setContentType("text/plain");
        // response.getWriter().write("Hello " + name + "\n");
        // response.getWriter().write(displayService.getWelcomeMessage());

        String site = "TripNest";
        String country = "India";
        response.setContentType("application/json");
        String json = "{\"site\": \"" + site + "\", \"country\": \"" + country + "\"}";
        response.getWriter().write(json);
    }
}
