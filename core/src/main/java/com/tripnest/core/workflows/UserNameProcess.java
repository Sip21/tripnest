package com.tripnest.core.workflows;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.tripnest.core.services.CountryService;
import com.tripnest.core.services.UsernameService;

@Component(service = WorkflowProcess.class, property = {
        "process.label=UserName Workflow Payload"
})
public class UserNameProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(UserNameProcess.class);

    @Reference
    private UsernameService service;

    @Override
    public void execute(WorkItem item, WorkflowSession session, MetaDataMap args) throws WorkflowException {
        String payload = item.getWorkflowData().getPayload().toString();
        LOG.info("Payload = {}", payload);
        String response = service.getUsername();
        LOG.info("API Response = {}", response);
    }

}
