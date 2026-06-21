package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest - New Api Configuration", description = "This is the new Configuration for API")
public @interface NewApiConfig {

    @AttributeDefinition(name = "Enable Flag", description = "Do we need to Enable /Disbale the Flag")
    boolean enableFlag();

    @AttributeDefinition(name = "URL", description = "Please enter the URL for the API")
    String apiUrl();
}
