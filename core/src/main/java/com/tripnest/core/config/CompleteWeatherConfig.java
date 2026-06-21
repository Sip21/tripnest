package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Complete Weather Scheduler Config")
public @interface CompleteWeatherConfig {

    @AttributeDefinition(name = "Scheduler Expression", description = "Enter the Cron Expression")
    String schedulerExpression() default "0/10 * * * * ?";
}
