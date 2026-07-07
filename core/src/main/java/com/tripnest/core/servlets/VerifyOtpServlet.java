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
        "sling.servlet.paths=/bin/tripnest/verifyotp",
        "sling.servlet.methods=POST"
})
public class VerifyOtpServlet extends SlingAllMethodsServlet {

    @Reference
    private AuthenticationService authenticationService;

    @Override
    protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String mobileNumber = request.getParameter("mobile");
        String otp = request.getParameter("otp");

        boolean isVerified = authenticationService.verifyOtp(mobileNumber, otp);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        if (isVerified) {
            response.getWriter().write("{\"message\":\"OTP Verified Successfully\"}");
        } else {
            response.setStatus(SlingHttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"message\":\"Invalid OTP\"}");
        }

    }
}
