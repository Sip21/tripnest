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

import com.tripnest.core.config.SimpleConfig;

@Component(service = SimpleScheduler.class, immediate = true)
@Designate(ocd = SimpleConfig.class)
public class SimpleScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(SimpleScheduler.class);

    private static final String SCHEDULE_ID = "SimpleConfig";

    @Reference
    private Scheduler scheduler;

    private boolean enableFlag;
    private String cronExpression;

    @Activate
    @Modified
    protected void activate(SimpleConfig config) {
        this.enableFlag = config.enableFlag();
        this.cronExpression = config.cronExpression();

        removeScheduler();
        if (enableFlag) {
            addScheduler();
            LOG.info("**SimpleScheduler Activated**");
        }

    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name(SCHEDULE_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("**SimpleScheduler Added**");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULE_ID);
        LOG.info("**SimpleScheduler Removed**");
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
        LOG.info("**SimpleScheduler Deactivated**");
    }

    @Override
    public void run() {
        LOG.info("**SimpleScheduler Running**");
    }

}
