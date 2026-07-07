package com.tripnest.core.services.impl;

import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.IMSConfig;
import com.tripnest.core.services.IMSService;

@Component(service = IMSService.class, immediate = true)
@Designate(ocd = IMSConfig.class)
public class IMSServiceImpl implements IMSService {

    private static final Logger LOG = LoggerFactory.getLogger(IMSServiceImpl.class);

    private String clientId;
    private String clientSecret;
    private String orgId;
    private String scopes;

    @Activate
    @Modified
    protected void activate(IMSConfig config) {
        this.clientId = config.clientId();
        this.clientSecret = config.clientSecret();
        this.orgId = config.orgId();
        this.scopes = config.scopes();
    }

    @Override
    public String getIMSValues() {
        LOG.info("clientId ::: " + clientId);
        LOG.info("clientSecret ::: " + clientSecret);
        LOG.info("orgId ::: " + orgId);
        LOG.info("scopes ::: " + scopes);
        return clientId + " | " + clientSecret + " | " + orgId + " | " + scopes;
    }

}
