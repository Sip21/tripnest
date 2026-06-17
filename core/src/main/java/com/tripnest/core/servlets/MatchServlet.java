package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.MatchService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/search",
        "sling.servlet.methods=GET"
})
public class MatchServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private MatchService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String urltitle = request.getParameter("title");
        if (urltitle == null || urltitle.isEmpty()) {
            response.getWriter().println("Title parameter missing");
            return;
        }
        List<String> pagePath = service.getFromURL(urltitle);
        for (String path : pagePath) {
            response.setContentType("text/plain");
            response.getWriter().write(path);
        }

    }

}
