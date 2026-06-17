package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.NameService;

@Component(service = Servlet.class, immediate = true, property = {
        "sling.servlet.paths=/bin/userinfo",
        "sling.servlet.methods=GET"
})
public class NameServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Reference
    private NameService nameService;

    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        response.setContentType("text/plain");
        String name = request.getParameter("name");
        String message = nameService.getName(name);
        response.getWriter().write(message);
    }

}
