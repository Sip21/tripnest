package com.tripnest.core.listeners;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.LoginException;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = ResourceChangeListener.class, immediate = true, property = {
        ResourceChangeListener.PATHS + "=/content/tripnest",
        ResourceChangeListener.CHANGES + "=CHANGED"
})
public class ContentAuditResourceListener implements ResourceChangeListener {
    private static final Logger LOG = LoggerFactory.getLogger(ContentAuditResourceListener.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void onChange(List<ResourceChange> changes) {
        Map<String, Object> param = new HashMap<>();
        param.put(resolverFactory.SUBSERVICE, "tripnest-subservice");

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(param)) {
            for (ResourceChange change : changes) {
                String path = change.getPath();
                Resource resource = resolver.getResource(path);
                if (resource != null) {
                    String user = resource.getValueMap().get("cq:lastModifiedBy", String.class);
                    LOG.info("Page Modified: {}", path);
                    LOG.info("Last Modified By: {}", user);
                }
            }

        } catch (LoginException e) {
            LOG.error("LoginException Found {}", e.getMessage());
        }

    }

}
