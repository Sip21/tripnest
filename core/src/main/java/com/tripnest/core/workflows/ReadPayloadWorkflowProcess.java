package com.tripnest.core.workflows;

import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {
        "process.label=Read Workflow Payload"
})
public class ReadPayloadWorkflowProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(ReadPayloadWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {

        WorkflowData workflowData = workItem.getWorkflowData();
        String payloadPath = workflowData.getPayload().toString();
        LOG.info("Payload Path : {}", payloadPath);
    }

}
