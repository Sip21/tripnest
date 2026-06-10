package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

//Definition of the configuration
@ObjectClassDefinition(name = "TripNest - Site Configuration", description = "General site-level configuration for TripNest")
public @interface SiteConfig {

    // Definition of the Attribute
    @AttributeDefinition(name = "Site Name", description = "The display name of the site")

    String siteName() default "Tripnest";

}
