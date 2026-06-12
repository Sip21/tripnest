package com.tripnest.core.schedulers;

import java.util.concurrent.atomic.AtomicInteger;

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

import com.tripnest.core.config.CounterConfig;

@Component(immediate = true)
@Designate(ocd = CounterConfig.class)
public class CounterScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(CounterScheduler.class);

    private static final String SCHEDULER_ID = "CounterScheduler";

    @Reference
    private Scheduler scheduler;

    private boolean enableFlag;
    private String cronExpression;
    private final AtomicInteger counter = new AtomicInteger();

    @Activate
    @Modified
    protected void activate(CounterConfig config) {
        this.enableFlag = config.enableFlag();
        this.cronExpression = config.cronExpression();

        // reset counter on config change
        counter.set(0);

        removeScheduler();
        if (enableFlag) {
            // addScheduler();
        } else {
            LOG.info("CounterScheduler is disabled.");
        }

    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
    }

    private void removeScheduler() {
        LOG.info("*****CounterScheduler removed successfully****");
        scheduler.unschedule(SCHEDULER_ID);
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("****CounterScheduler added successfully****");
    }

    @Override
    public void run() {
        int current = counter.incrementAndGet();
        LOG.info("****CounterScheduler running successfully****");
        LOG.info("Counter = {}", current);
    }

}
