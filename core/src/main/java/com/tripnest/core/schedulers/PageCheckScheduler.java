package com.tripnest.core.schedulers;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
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

import com.tripnest.core.config.PageCheckConfig;

@Component(service = PageCheckScheduler.class, immediate = true)
@Designate(ocd = PageCheckConfig.class)
public class PageCheckScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(PageCheckScheduler.class);
    private static final String SCHEDULER_ID = "PageCheckScheduler";

    @Reference
    private Scheduler scheduler;

    @Reference
    ResourceResolverFactory resolverFactory;

    private boolean enabledFlag;
    private String cronExpression;
    private String path;

    @Activate
    @Modified
    protected void activate(PageCheckConfig config) {
        this.enabledFlag = config.enabledFlag();
        this.cronExpression = config.cronExpression();
        this.path = config.path();

        removeScheduler();
        if (enabledFlag) {
            addScheduler();
        } else {
            LOG.info("***PageCheckScheduler DISABLED successfully***");
        }
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(cronExpression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
        LOG.info("***PageCheckScheduler ADDED successfully***");
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
        LOG.info("***PageCheckScheduler REMOVED successfully***");
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
        LOG.info("***PageCheckScheduler DEACTIVATED successfully***");
    }

    @Override
    public void run() {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            Resource resource = resolver.getResource(path);
            if (resource != null) {
                LOG.info("***Page Exists : {} ***", path);
                LOG.info("***PageCheckScheduler RUNNING successfully***");
            } else {
                LOG.info("***Page Missing : {} ***", path);
            }

        } catch (LoginException e) {
            LOG.error("Failed to get ResourceResolver: {}", e.getMessage());
        }

    }

}
