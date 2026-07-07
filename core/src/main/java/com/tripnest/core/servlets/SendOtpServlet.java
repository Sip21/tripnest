package com.tripnest.core.servlets;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

import com.tripnest.core.services.AuthenticationService;

@Component(service = Servlet.class, property = {
                "sling.servlet.paths=/bin/tripnest/sendotp",
                "sling.servlet.methods=POST"
})
public class SendOtpServlet extends SlingAllMethodsServlet {

        @Reference
        private AuthenticationService authenticationService;

        @Override
        protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
                        throws ServletException, IOException {

                String mobileNumber = request.getParameter("mobile");
                authenticationService.sendOtp(mobileNumber);
                response.setContentType("application/json");
                response.setCharacterEncoding("UTF-8");
                response.getWriter().write("{\"message\":\"OTP Sent Successfully\"}");
        }
}
