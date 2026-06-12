package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.TestEventService;

@Component(service = TestEventService.class, immediate = true)
public class TestEventServiceImpl implements TestEventService {

    private static final Logger LOG = LoggerFactory.getLogger(TestEventServiceImpl.class);

    private static final String EVENT_TOPIC = "tripnest/event/test";

    @Reference
    private EventAdmin eventAdmin;

    @Override
    public void fireEvent() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("source", "TestEventService");
        Event event = new Event(EVENT_TOPIC, properties);
        eventAdmin.sendEvent(event);
        LOG.info("*** Event fired on topic: {} ***", EVENT_TOPIC);
    }

}
