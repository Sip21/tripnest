package com.tripnest.core.listeners;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.osgi.service.event.EventConstants;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=tripnest/event/test" })
public class TestEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(TestEventHandler.class);

    @Override
    public void handleEvent(Event event) {
        LOG.info("*** Event Received ***");
        LOG.info("*** Topic: {} ***", event.getTopic());
    }

}
