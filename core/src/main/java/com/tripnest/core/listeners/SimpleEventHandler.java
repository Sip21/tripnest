package com.tripnest.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = EventConstants.EVENT_TOPIC
        + "=content/tripnest/simple")
public class SimpleEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleEventHandler.class);

    @Override
    public void handleEvent(Event event) {
        String username = (String) event.getProperty("username");
        String action = (String) event.getProperty("action");
        LOG.info("User {} {} ", username, action);
    }

}
