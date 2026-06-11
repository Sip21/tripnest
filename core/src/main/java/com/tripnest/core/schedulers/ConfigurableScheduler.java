package com.tripnest.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.config.ConfigurableSchedulerConfig;

//connect to config
@Component(immediate = true)
@Designate(ocd = ConfigurableSchedulerConfig.class)
public class ConfigurableScheduler implements Runnable {

    // Add Log
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurableScheduler.class);

    // Give this scheduler a name
    private static final String SCHEDULER_ID = "ConfigurableScheduler";

    // call scheduler via reference
    @Reference
    private Scheduler scheduler;

    // add parameters here
    private boolean enabledFlag;
    private String cronExpression;

    // First we have activate method
    @Activate
    @Modified
    protected void activate(ConfigurableSchedulerConfig config) {
        this.enabledFlag = config.enabledFlag();
        this.cronExpression = config.cronExpression();

        // remove scheduler
        removeScheduler();

        if (enabledFlag) {
            // addScheduler();
        } else {
            LOG.info("ConfigurableScheduler is disabled, not scheduling.");
        }

    }

    // Now we have Deactivate Method
    @Deactivate
    protected void deactivate() {
        removeScheduler();
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("ConfigurableScheduler added with cron: {}", cronExpression);
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
    }

    @Override
    public void run() {
        LOG.info("Configurable Scheduler is running.");
    }

}
