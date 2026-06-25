package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.SimpleService;

@Component(service = SimpleService.class, immediate = true)
public class SimpleServiceImpl implements SimpleService {

    private static final Logger LOG = LoggerFactory.getLogger(SimpleServiceImpl.class);

    private static final String EVENT_TOPIC = "content/tripnest/simple";

    @Reference
    private EventAdmin eventAdmin;

    @Override
    public void fireAuditEvent() {
        Map<String, Object> properties = new HashMap<>();
        properties.put("username", "Supriya");
        properties.put("action", "created page");

        Event event = new Event(EVENT_TOPIC, properties);
        // LOG.info("*** Before sendEvent ***");
        eventAdmin.sendEvent(event);
        // LOG.info("*** SimpleServiceImpl called ***");
    }

}
