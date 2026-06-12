package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Tripnest - Counter Configuration", description = "This is counter configuration for tripnest website")
public @interface CounterConfig {

    @AttributeDefinition(name = "Enabled", description = "Enable or disable the counter scheduler")

    boolean enableFlag();

    @AttributeDefinition(name = "Cron Expression", description = "Creating cron expression")

    String cronExpression();

}
