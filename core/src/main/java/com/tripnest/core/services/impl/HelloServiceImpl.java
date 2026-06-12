package com.tripnest.core.services.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventAdmin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tripnest.core.services.HelloService;

@Component(service = HelloService.class, immediate = true)
public class HelloServiceImpl implements HelloService {

    private static final Logger LOG = LoggerFactory.getLogger(HelloServiceImpl.class);

    private static final String EVENT_TOPIC = "content/tripnest/hello";

    @Reference
    private EventAdmin eventAdmin;

    @Override
    public void publishMessage(String greet) {
        Map<String, Object> properties = new HashMap<>();
        properties.put("source", "HelloService");
        properties.put("greet", greet);

        Event event = new Event(EVENT_TOPIC, properties);
        eventAdmin.sendEvent(event);
        LOG.info("***HelloServiceImpl called***");
    }

}
