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

import com.tripnest.core.config.GreetingConfig;
import com.tripnest.core.services.GreetingService;

@Component(service = GreetingScheduler.class, immediate = true)
@Designate(ocd = GreetingConfig.class)
public class GreetingScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(GreetingScheduler.class);

    private static final String SCHEDULE_ID = "GreetingScheduler";

    @Reference
    private Scheduler scheduler;

    @Reference
    private GreetingService service;

    private boolean enabledFlag;
    private String cronExpression;

    @Activate
    @Modified
    protected void activate(GreetingConfig config) {
        LOG.info("***GreetingScheduler activated***");
        this.enabledFlag = config.enableFlag();
        this.cronExpression = config.cronExpression();

        removeScheduler();
        if (enabledFlag) {
            // addScheduler();
        } else {
            LOG.info("***GreetingScheduler DISABLED***");
        }
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
        LOG.info("***GreetingScheduler deactivated***");
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name(SCHEDULE_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("***GreetingScheduler added successfully***");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULE_ID);
        LOG.info("***GreetingScheduler removed successfully***");
    }

    @Override
    public void run() {
        if (service != null) {
            LOG.info("Greeting: {}", service.getGreeting()); // ← correct pattern
            LOG.info("***GreetingScheduler running***");
        } else {
            LOG.error("*** GreetingService is NULL ****");
        }

    }

}
