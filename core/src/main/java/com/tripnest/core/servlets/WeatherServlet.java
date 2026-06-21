package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tripnest.core.models.WeatherPOJO;
import com.tripnest.core.services.WeatherService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/weathercall",
        "sling.servlet.methods=GET"
})
public class WeatherServlet extends SlingSafeMethodsServlet {

    @Reference
    private WeatherService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        WeatherPOJO weather = service.getWeather();
        ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json");
        response.getWriter().write(mapper.writeValueAsString(weather));
    }
}
