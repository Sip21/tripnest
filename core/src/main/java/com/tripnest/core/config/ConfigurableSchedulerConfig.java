package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

//Definition of the config
@ObjectClassDefinition(name = "Configuration - Tripnest", description = "This is teh Tripnest configuration for Scheduler")
public @interface ConfigurableSchedulerConfig {

    @AttributeDefinition(name = "Enabled", description = "Enabled Flag for scheduler")

    boolean enabledFlag() default true;

    @AttributeDefinition(name = "Cron Expression", description = "Cron expression for scheduler")

    String cronExpression() default "0/15 * * * * ?";

}
