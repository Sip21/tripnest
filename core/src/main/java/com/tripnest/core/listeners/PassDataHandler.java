package com.tripnest.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class, immediate = true, property = EventConstants.EVENT_TOPIC
        + "=tripnest/event/passdata")
public class PassDataHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PassDataHandler.class);

    @Override
    public void handleEvent(Event event) {
        String userName = (String) event.getProperty("name");
        LOG.info("*** Event Received, name = {} ***", userName);
        LOG.info("*** Topic: {} ***", event.getTopic());
    }

}
