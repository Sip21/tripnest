package com.tripnest.core.models;

import javax.annotation.PostConstruct;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class AuthorDetailModel {

    @ValueMapValue
    private String name;

    @ValueMapValue
    private String bio;

    @ValueMapValue
    private String designation;

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public String getBio() {
        return bio;
    }

    private String displayName;

    @PostConstruct
    protected void init() {
        if (designation != null && !designation.isEmpty()) {
            displayName = name + " - " + designation;
        } else {
            displayName = name;
        }
    }

    public String getDisplayName() {
        return displayName;
    }

}
