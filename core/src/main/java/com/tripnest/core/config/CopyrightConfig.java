package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Copyright Configuration", description = "Copyright configuration for TripNest")
public @interface CopyrightConfig {

    @AttributeDefinition(name = "Copyright Text", description = "The text to be displayed in the footer for copyright information")

    String copyrightText();

}
