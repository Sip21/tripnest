package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AssetReportService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/assetreport",
        "sling.servlet.methods=GET"
})
public class AssetReportServlet extends SlingSafeMethodsServlet {

    @Reference
    private AssetReportService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        response.setContentType("text/plain");
        response.getWriter()
                .write("Total Assets : " + service.getAssetCount());
    }
}
