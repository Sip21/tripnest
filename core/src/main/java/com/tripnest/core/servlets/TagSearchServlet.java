package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TagSearchService;

@Component(service = Servlet.class, immediate = true, property = {
        "sling.servlet.paths=/bin/tagsearch",
        "sling.servlet.methods=GET"
})
public class TagSearchServlet extends SlingSafeMethodsServlet {

    @Reference
    private TagSearchService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        String tag = request.getParameter("tag");
        ResourceResolver resolver = request.getResourceResolver();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(String.join("\n", service.getPages(resolver, tag)));
    }

}
