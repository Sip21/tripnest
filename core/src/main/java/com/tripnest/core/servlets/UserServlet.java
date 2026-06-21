package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.models.UserPOJO;
import com.tripnest.core.services.UserService;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/user",
        "sling.servlet.methods=GET"
})
public class UserServlet extends SlingSafeMethodsServlet {

    @Reference
    private UserService service;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws IOException {

        UserPOJO user = service.getUserDetails();
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(
                "Name : " + user.getName()
                        + "\nEmail : " + user.getEmail()
                        + "\nCity : " + user.getCity());
    }

}
