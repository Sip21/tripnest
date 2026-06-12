package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.PassDataService;

@Component(service = PassDataService.class, immediate = true)
public class PassDataServiceImpl implements PassDataService {

    private static final Logger LOG = LoggerFactory.getLogger(PassDataServiceImpl.class);

    private static final String EVENT_TOPIC = "tripnest/event/passdata";

    @Reference
    private EventAdmin eventAdmin;

    @Override
    public void getName(String name) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("name", name);

        Event event = new Event(EVENT_TOPIC, properties);
        eventAdmin.sendEvent(event);
        LOG.info("*** Event fired with name: {} ***", name);
    }

}
