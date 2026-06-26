package com.tripnest.core.workflows;

import javax.jcr.Session;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.dam.api.Asset;

@Component(service = WorkflowProcess.class, property = {
        "process.label=Read Asset Metadata"
})
public class ReadAssetMetadataWorkflowProcess implements WorkflowProcess {

    private static final Logger LOG = LoggerFactory.getLogger(ReadAssetMetadataWorkflowProcess.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap metaDataMap)
            throws WorkflowException {

        String payloadPath = workItem.getWorkflowData().getPayload().toString();
        Session session = workflowSession.adaptTo(Session.class);
        ResourceResolver resolver = workflowSession.adaptTo(ResourceResolver.class);
        Resource resource = resolver.getResource(payloadPath);
        if (resource == null) {
            LOG.warn("Asset not found : {}", payloadPath);
            return;
        }
        Asset asset = resource.adaptTo(Asset.class);
        if (asset == null) {
            LOG.warn("Resource is not an Asset");
            return;
        }
        String title = asset.getMetadataValue("dc:title");
        String description = asset.getMetadataValue("dc:description");

        LOG.info("Title : {}", title);
        LOG.info("Description : {}", description);
    }

}
