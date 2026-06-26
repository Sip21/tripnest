package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.ContentFragmentResponse;
import com.tripnest.core.services.ContentFragmentService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/readallcontentfragments",
        "sling.servlet.methods=GET"
})
public class ReadAllContentFragmentServlet extends SlingSafeMethodsServlet {
    @Reference
    private ContentFragmentService service;

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws IOException {

        List<ContentFragmentResponse> fragments = service.getAllFragments(
                "/content/dam/tripnest/tripnest-cf");

        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), fragments);

    }
}
