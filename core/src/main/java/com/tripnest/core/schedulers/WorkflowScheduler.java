package com.tripnest.core.schedulers;

import java.util.Collections;

import javax.jcr.Session;

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

import com.day.cq.workflow.WorkflowService;
import com.day.cq.workflow.WorkflowSession;
import com.day.cq.workflow.exec.WorkflowData;
import com.day.cq.workflow.model.WorkflowModel;
import com.tripnest.core.config.WorkflowSchedulerConfig;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = WorkflowSchedulerConfig.class)
public class WorkflowScheduler implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(WorkflowScheduler.class);

    private static final String SCHEDULER_ID = "WorkflowScheduler";

    @Reference
    private Scheduler scheduler;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private WorkflowService workflowService;

    private String scheduler_expression;
    private boolean enabled;

    @Activate
    @Modified
    protected void activate(WorkflowSchedulerConfig config) {
        this.enabled = config.enabled();
        this.scheduler_expression = config.scheduler_expression();

        if (!enabled) {
            return;
        }
        removeScheduler();
        addScheduler();
    }

    private void addScheduler() {
        ScheduleOptions options = scheduler.EXPR(scheduler_expression);
        options.name(SCHEDULER_ID);
        options.canRunConcurrently(false);
        scheduler.schedule(this, options);
    }

    @Deactivate
    protected void deactivate() {
        removeScheduler();
    }

    private void removeScheduler() {
        scheduler.unschedule(SCHEDULER_ID);
    }

    @Override
    public void run() {
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Collections.singletonMap(
                        ResourceResolverFactory.SUBSERVICE,
                        "workflow-service"))) {

            WorkflowSession workflowSession = workflowService.getWorkflowSession(
                    resolver.adaptTo(Session.class));

            WorkflowModel model = workflowSession.getModel(
                    "/var/workflow/models/Asset_Logger_Workflow");

            WorkflowData data = workflowSession.newWorkflowData(
                    "JCR_PATH",
                    "/content/dam/tripnest/asset.jpg");

            workflowSession.startWorkflow(model, data);

        } catch (Exception e) {

            LOG.error("Unable to start workflow", e);

        }

    }

}
