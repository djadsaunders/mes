package com.djad.mes.events;

import java.util.Properties;

public interface EventSender {
    
    public static enum Topic {
        MES("mes"),
        RAW("raw");

        private String topicName;

        private Topic(String topicName) {
            this.topicName = topicName;
        }

        public String getName() {
            return this.topicName;
        }
    }

    void sendEvent(Event event, Topic topic);
    Properties getProperties();
}
