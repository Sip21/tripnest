package com.tripnest.core.listeners;

import java.util.List;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.AuditService;

@Component(service = ResourceChangeListener.class, immediate = true, property = {
        ResourceChangeListener.PATHS + "=/content/tripnest",
        ResourceChangeListener.CHANGES + "=ADDED"
})
public class AuditResourceListener implements ResourceChangeListener {

    private static final Logger LOG = LoggerFactory.getLogger(AuditResourceListener.class);

    @Reference
    AuditService auditService;

    @Override
    public void onChange(List<ResourceChange> changes) {
        for (ResourceChange change : changes) {
            String path = change.getPath();
            String message = auditService.getAuditMessage();
            LOG.info("** {} at path: {} **", message, path);
        }
    }

}
