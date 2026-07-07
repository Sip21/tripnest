package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AdobeApiService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/adobeapi",
        "sling.servlet.methods=GET"
})
public class AdobeApiServlet extends SlingSafeMethodsServlet {

    @Reference
    private AdobeApiService adobeApiService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String endpoint = "https://jsonplaceholder.typicode.com/users/1";

        String json = adobeApiService.callApi(endpoint);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
