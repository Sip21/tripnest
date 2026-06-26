package com.tripnest.core.workflows;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;

@Component(service = WorkflowProcess.class, property = {
        "process.label=Update Asset Metadata"
})
public class UpdateMetadataProcess implements WorkflowProcess {
    private static final Logger LOG = LoggerFactory.getLogger(UpdateMetadataProcess.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void execute(WorkItem item, WorkflowSession session,
            MetaDataMap args) throws WorkflowException {

        String payloadPath = item.getWorkflowData().getPayload().toString();

        try (ResourceResolver resolver = session.adaptTo(ResourceResolver.class)) {

            Resource metadataResource = resolver.getResource(
                    payloadPath + "/jcr:content/metadata");

            if (metadataResource != null) {

                ModifiableValueMap properties = metadataResource.adaptTo(ModifiableValueMap.class);

                properties.put("processed", true);

                resolver.commit();

                LOG.info("Updated processed=true for {}", payloadPath);

            }

        } catch (Exception e) {
            throw new WorkflowException(e);
        }
    }

}
