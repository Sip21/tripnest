package com.tripnest.core.schedulers;

import java.time.LocalDateTime;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.SchedulerInfoService;

@Component(immediate = true)
public class SchedulerInfoJob implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(SchedulerInfoJob.class);

    private static final String SCHEDULE_ID = "SchedulerInfoJob";

    @Reference
    private Scheduler scheduler;

    @Reference
    private SchedulerInfoService schedulerInfoService;

    @Activate
    protected void activate() {
        removeScheduler();
        if (schedulerInfoService != null) {
            // addScheduler();
            LOG.info("***SchedulerInfoJob activated successfully***");
        }
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR("0/10 * * * * ?");
        options.name(SCHEDULE_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("***SchedulerInfoJob added successfully***");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULE_ID);
        LOG.info("***SchedulerInfoJob removed successfully***");
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
        LOG.info("***SchedulerInfoJob deactiavted successfully***");
    }

    @Override
    public void run() {
        String currentTime = LocalDateTime.now().toString();
        schedulerInfoService.setLastRunTime(currentTime);
        LOG.info("***SchedulerInfoJob running successfully***");
    }

}
