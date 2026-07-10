package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.LocalizationReportPOJO;
import com.tripnest.core.services.LocalizationService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/localization",
        "sling.servlet.methods=GET"
})
public class LocalizationServlet extends SlingSafeMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(LocalizationServlet.class);

    @Reference
    private LocalizationService localizationService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String pagePath = request.getParameter("page");
        PageManager pageManager = request.getResourceResolver().adaptTo(PageManager.class);
        Page currentPage = pageManager.getPage(pagePath);

        LocalizationReportPOJO report = localizationService.getLocalizationReport(currentPage);
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(report));
    }

}
