package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest IMS Configuration", description = "This is AMS IMS Configuration for Tripnest Website")
public @interface IMSConfig {

    @AttributeDefinition(name = "Client ID", description = "Enter the Client Id")
    String clientId();

    @AttributeDefinition(name = "Client Secret", description = "Enter the Client Secret")
    String clientSecret();

    @AttributeDefinition(name = "Organization ID", description = "Enter the Organization Id")
    String orgId();

    @AttributeDefinition(name = "Scopes", description = "Enter the Scopes")
    String scopes();
}
