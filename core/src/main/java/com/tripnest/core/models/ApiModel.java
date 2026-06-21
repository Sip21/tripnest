package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.OSGiService;

import com.tripnest.core.services.ApiService;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ApiModel {

    @OSGiService
    private ApiService service;

    private String city;

    @PostConstruct
    protected void init() {
        ApiPOJO api = service.getAPiDetail();
        city = api.getCityName();
    }

    public String getCity() {
        return city;
    }
}
