package com.tripnest.core.services.impl;

import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.Designate;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.OtpProviderService;

@Component(service = OtpProviderService.class)
@Designate(ocd = OtpProviderServiceImpl.Configuration.class)
public class OtpProviderServiceImpl implements OtpProviderService {

    private static final Logger LOG = LoggerFactory.getLogger(OtpProviderServiceImpl.class);

    private final HttpClient httpClient = HttpClient.newHttpClient();

    @ObjectClassDefinition(name = "TripNest Twilio Configuration")
    public @interface Configuration {

        @AttributeDefinition(name = "Account SID")
        String accountSid();

        @AttributeDefinition(name = "Auth Token")
        String authToken();

        @AttributeDefinition(name = "Verify Service SID")
        String verifyServiceSid();
    }

    private String accountSid;
    private String authToken;
    private String verifyServiceSid;

    @Activate
    protected void activate(Configuration config) {
        this.accountSid = config.accountSid();
        this.authToken = config.authToken();
        this.verifyServiceSid = config.verifyServiceSid();
        LOG.info("Twilio configuration loaded.");
        LOG.info("Account SID : {}", accountSid);
        LOG.info("Verify Service SID : {}", verifyServiceSid);
    }

    @Override
    public void sendOtp(String mobileNumber) {
        String body = "To=" + URLEncoder.encode(mobileNumber, StandardCharsets.UTF_8)
                + "&Channel=sms";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://verify.twilio.com/v2/Services/"
                                + verifyServiceSid
                                + "/Verifications"))
                .header("Authorization", getBasicAuth())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            LOG.info("Status Code : {}", response.statusCode());
            LOG.info("Response : {}", response.body());

        } catch (IOException | InterruptedException e) {
            LOG.error("Error while sending OTP", e);
            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public boolean verifyOtp(String mobileNumber, String otp) {
        String body = "To=" + URLEncoder.encode(mobileNumber, StandardCharsets.UTF_8)
                + "&Code=" + URLEncoder.encode(otp, StandardCharsets.UTF_8);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(
                        "https://verify.twilio.com/v2/Services/"
                                + verifyServiceSid
                                + "/VerificationCheck"))
                .header("Authorization", getBasicAuth())
                .header("Content-Type", "application/x-www-form-urlencoded")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {

            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

            LOG.info("Status Code : {}", response.statusCode());
            LOG.info("Response : {}", response.body());

            return response.statusCode() == 200
                    && response.body().contains("\"status\": \"approved\"");

        } catch (IOException | InterruptedException e) {

            LOG.error("Error verifying OTP", e);

            if (e instanceof InterruptedException) {
                Thread.currentThread().interrupt();
            }
        }

        return false;
    }

    private String getBasicAuth() {
        String credentials = accountSid + ":" + authToken;
        return "Basic " + Base64.getEncoder()
                .encodeToString(credentials.getBytes(StandardCharsets.UTF_8));
    }

}
