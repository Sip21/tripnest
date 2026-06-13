package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import com.tripnest.core.models.PageTitleModel;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/pagetitle",
        "sling.servlet.methods=GET"
})
public class PageTitleServlet extends SlingSafeMethodsServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        PageTitleModel model = request.adaptTo(PageTitleModel.class);
        response.setContentType("text/plain");
        // response.setContentType("application/json");
        if (model != null) {
            // String json = String.format("{ \"pageTitle\": \"%s\" }",
            // model.getPageTitle());
            // response.getWriter().write(json);
            response.getWriter().write("Page Title :: " + model.getPageTitle());
        } else {
            response.getWriter().write("{ \"error\": \"Model is null\" }");
        }
    }

}
