package com.tripnest.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Workflow Scheduler")
public @interface WorkflowSchedulerConfig {
    @AttributeDefinition(name = "Cron Expression")
    // String scheduler_expression() default "0/10 * * * * ?";
    String scheduler_expression();

    @AttributeDefinition(name = "Enabled")
    boolean enabled() default true;
}
