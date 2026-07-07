package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.IMSTokenService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/tripnest/imstoken",
        "sling.servlet.methods=GET"
})
public class IMSTokenServlet extends SlingSafeMethodsServlet {

    @Reference
    private IMSTokenService imsTokenService;

    @Override
    protected void doGet(SlingHttpServletRequest request,
            SlingHttpServletResponse response)
            throws IOException {

        response.setContentType("text/plain");
        response.getWriter().write(imsTokenService.getAccessToken());
    }

}
