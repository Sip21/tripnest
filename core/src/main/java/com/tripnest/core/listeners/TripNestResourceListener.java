package com.tripnest.core.listeners;

import java.util.List;

import org.apache.sling.api.resource.observation.ResourceChange;
import org.apache.sling.api.resource.observation.ResourceChangeListener;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.drew.lang.annotations.NotNull;

@Component(service = ResourceChangeListener.class, immediate = true, property = {
        ResourceChangeListener.PATHS + "=/content/tripnest",
        ResourceChangeListener.CHANGES + "=ADDED",
        ResourceChangeListener.CHANGES + "=CHANGED",
        ResourceChangeListener.CHANGES + "=REMOVED"
})
public class TripNestResourceListener implements ResourceChangeListener {

    private static final Logger LOG = LoggerFactory.getLogger(TripNestResourceListener.class);

    @Override
    public void onChange(List<ResourceChange> changes) {
        for (ResourceChange change : changes) {
            switch (change.getType()) {
                case ADDED:
                    LOG.info("*** Page Added: {} ***", change.getPath());
                    break;
                case CHANGED:
                    LOG.info("*** Resource Changed: {} ***", change.getPath());
                    break;
                case REMOVED:
                    LOG.info("*** Resource Removed: {} ***", change.getPath());
                    break;
                default:
                    LOG.info("*** Unknown change at: {} ***", change.getPath());
            }
        }

    }

}
