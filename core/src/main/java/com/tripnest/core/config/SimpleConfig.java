package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest - Simple Configuration", description = "This is a simple configuration")
public @interface SimpleConfig {

    @AttributeDefinition(name = "Enable Flag", description = "Flag to enable or disable the scheduler")

    boolean enableFlag();

    @AttributeDefinition(name = "Cron Expression", description = "Please enter the cron expression here")

    String cronExpression();

}
