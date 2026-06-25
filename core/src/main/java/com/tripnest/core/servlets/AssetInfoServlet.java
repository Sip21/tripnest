package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AssetService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/assetinfo",
        "sling.servlet.methods=GET"
})
public class AssetInfoServlet extends SlingSafeMethodsServlet {

    @Reference
    private AssetService assetService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        assetService.printAssetDetails("/content/dam/tripnest/pdf/Travel Brochure Example Blank.pdf");
        response.getWriter().write("Asset details printed in logs");
    }
}
