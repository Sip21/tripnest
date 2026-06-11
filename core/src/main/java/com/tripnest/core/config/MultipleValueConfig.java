package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

//Definition of Configuration
@ObjectClassDefinition(name = "MultipleValue - Tripnest Configuration", description = "This is multivalue configuration for Tripnest Website")
public @interface MultipleValueConfig {

    @AttributeDefinition(name = "Site Name", description = "Name of the website")
    String siteName();

    @AttributeDefinition(name = "Support Email", description = "Email of the website")
    String supportEmail();

    @AttributeDefinition(name = "Country", description = "Country Name")
    String country();

}
