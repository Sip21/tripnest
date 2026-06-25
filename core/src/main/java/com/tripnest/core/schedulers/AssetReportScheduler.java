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

import com.tripnest.core.config.AssetReportConfiguration;
import com.tripnest.core.services.AssetReportService;

@Component(service = Runnable.class)
@Designate(ocd = AssetReportConfiguration.class)
public class AssetReportScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(AssetReportScheduler.class);
    private static final String SCHEDULER_ID = "AssetReportScheduler";

    @Reference
    private AssetReportService assetReportService;

    @Reference
    private Scheduler scheduler;

    private boolean enabled;
    private String scheduler_expression;

    @Activate
    @Modified
    protected void activate(AssetReportConfiguration config) {
        enabled = config.enabled();
        addScheduler();
        removeScheduler();
        LOG.info("AssetReportScheduler Activated");
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(scheduler_expression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("AssetReportScheduler Added");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
        LOG.info("AssetReportScheduler Removed");
    }

    @Deactivate
    protected void Deactivate(AssetReportConfiguration config) {
        removeScheduler();
        LOG.info("AssetReportScheduler Deactivated");
    }

    @Override
    public void run() {
        if (!enabled) {
            return;
        }
        assetReportService.generateReport();
        LOG.info("Asset Report Scheduler Running");
    }
}
