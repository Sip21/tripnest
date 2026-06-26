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
        "process.label=Read Workflow Arguments"
})
public class WorkflowArgumentsProcess implements WorkflowProcess {
    private static final Logger LOG = LoggerFactory.getLogger(WorkflowArgumentsProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {
        String processArgs = metaDataMap.get("PROCESS_ARGS", "");
        String country = processArgs.replace("Country=", "");
        LOG.info("Country = {}", country);
    }

}
