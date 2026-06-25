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
import com.tripnest.core.models.AssetReport;
import com.tripnest.core.services.SearchAssetService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/searchAsset",
        "sling.servlet.methods=GET"
})
public class SearchAssetServlet extends SlingSafeMethodsServlet {

    @Reference
    private SearchAssetService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {
        List<AssetReport> result = service.getPdfAssets();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        mapper.writeValue(response.getWriter(), result);
    }
}
