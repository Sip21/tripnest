package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest-Page Check Configuration", description = "This is page check configuration")
public @interface PageCheckConfig {

    @AttributeDefinition(name = "Enable Flag", description = "Enable/Disable the flag")

    boolean enabledFlag();

    @AttributeDefinition(name = "Path", description = "Enter the Content path here")

    String path();

    @AttributeDefinition(name = "CRON Expression", description = "Enter the Cron Expression here")

    String cronExpression();
}
