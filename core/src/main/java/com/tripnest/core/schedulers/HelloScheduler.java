package com.tripnest.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true)
public class HelloScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(HelloScheduler.class);

    private static final String SCHEDULER_ID = "HelloScheduler";

    @Reference
    private Scheduler scheduler;

    @Activate
    @Modified
    protected void activate() {
        removeScheduler();
        // addScheduler();
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
        LOG.info("HelloScheduler removed successfully");
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR("0/30 * * * * ?");
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("HelloScheduler added successfully");
    }

    @Override
    public void run() {
        LOG.info("Hello Scheduler");
    }

}
