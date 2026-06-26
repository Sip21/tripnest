package com.tripnest.core.workflows;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {
        "process.label=Asset Logger Process"
})
public class AssetLoggerProcess implements WorkflowProcess {
    private static final Logger LOG = LoggerFactory.getLogger(AssetLoggerProcess.class);

    @Override
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args)
            throws WorkflowException {

        String payload = item.getWorkflowData().getPayload().toString();

        LOG.info("Asset = {}", payload);

        LOG.info("Completed");
    }
}
