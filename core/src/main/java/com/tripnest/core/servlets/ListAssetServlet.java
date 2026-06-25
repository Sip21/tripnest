package com.tripnest.core.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.google.gson.Gson;
import com.tripnest.core.services.ListAssetService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/listassetinfo",
        "sling.servlet.methods=GET"
})
public class ListAssetServlet extends SlingSafeMethodsServlet {

    @Reference
    private ListAssetService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        List<String> assets = service.getListOfAssets("/content/dam/tripnest");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        Gson gson = new Gson();
        Map<String, Object> result = new HashMap<>();
        result.put("path", "/content/dam/tripnest");
        result.put("assets", assets);
        response.getWriter().write(gson.toJson(result));
    }
}
