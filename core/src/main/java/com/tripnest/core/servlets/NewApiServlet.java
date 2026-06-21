package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.NewApiPOJO;
import com.tripnest.core.services.NewApiService;

@Component(service = Servlet.class, property = {
                "sling.servlet.paths=/bin/getApiUrl",
                "sling.servlet.methods=GET"
})
public class NewApiServlet extends SlingSafeMethodsServlet {

        @Reference
        private NewApiService service;

        @Override
        protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

                System.out.println("Service = " + service);
                NewApiPOJO user = service.callApi();

                if (user == null) {
                        response.getWriter().write("API Failed");
                        return;
                }

                response.setContentType("application/json");
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(user));

        }
}