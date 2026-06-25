package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "TripNest Asset Report Scheduler")
public @interface AssetReportConfiguration {
    @AttributeDefinition(name = "Cron Expression")
    String scheduler_expression() default "0/10 * * * * ?";

    @AttributeDefinition(name = "Enabled")
    boolean enabled() default true;
}
