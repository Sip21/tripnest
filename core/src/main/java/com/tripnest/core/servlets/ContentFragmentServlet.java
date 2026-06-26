package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.services.ContentFragmentService;

@Component(service = Servlet.class, property = {
                "sling.servlet.paths=/bin/readfragment",
                "sling.servlet.methods=GET"
})
public class ContentFragmentServlet extends SlingSafeMethodsServlet {

        @Reference
        private ContentFragmentService service;

        @Override
        protected void doGet(SlingHttpServletRequest request,
                        SlingHttpServletResponse response)
                        throws IOException {

                Map<String, String> result = service.readFragment("/content/dam/tripnest/tripnest-cf/paris-cf");
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.writeValue(response.getWriter(), result);
        }
}
