package com.djad.mes.events;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class NoOpEventSender implements EventSender {
    private static final Logger logger = LoggerFactory.getLogger(NoOpEventSender.class);

    public void sendEvent(Event event, Topic topic) {
        logger.debug("Send event " + event + " to topic " + topic.getName());
    }

    public Properties getProperties() {
        return new Properties();
    }
}
