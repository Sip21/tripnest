package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.CreateAssetService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/createAsset",
        "sling.servlet.methods=GET"
})
public class CreateAssetServlet extends SlingSafeMethodsServlet {

    @Reference
    private CreateAssetService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        String result = service.createAssets();
        response.setContentType("text/plain");
        response.getWriter().write(result);
    }
}
