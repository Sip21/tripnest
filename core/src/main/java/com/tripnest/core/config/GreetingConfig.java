package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest - Greeting Configuration", description = "This is the configuration greeting")
public @interface GreetingConfig {

    @AttributeDefinition(name = "Enable Flag", description = "This is the Enable/Disable Flag")

    boolean enableFlag();

    @AttributeDefinition(name = "Cron Expression", description = "This is the cron expression to be entered")

    String cronExpression();

}
