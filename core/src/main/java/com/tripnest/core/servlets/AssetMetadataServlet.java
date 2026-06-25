package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.services.AssetMetadataService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/assetmetadata",
        "sling.servlet.methods=GET"
})
public class AssetMetadataServlet extends SlingSafeMethodsServlet {

    @Reference
    private AssetMetadataService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        // Map<String, String> metadata = service
        // .getAssetMetadata("/content/dam/tripnest/pdf/Travel Brochure Example
        // Blank.pdf");

        List<Map<String, String>> metadata = service.getAssetMetadata("/content/dam/tripnest");

        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(mapper.writeValueAsString(metadata));
    }
}
