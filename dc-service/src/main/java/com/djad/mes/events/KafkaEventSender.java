package com.djad.mes.events;

import java.util.Properties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class KafkaEventSender implements EventSender {
    private static final Logger logger = LoggerFactory.getLogger(KafkaEventSender.class);

    private Properties properties;

    private Producer<String ,String> producer;

    @Autowired
    public KafkaEventSender(KafkaProperties kafkaProperties) {
        this.properties = kafkaProperties;
    }

    @Override
    public void sendEvent(Event event, Topic topic) {
        logger.debug("Kafka event sender: send " + event);

        // Only start Kafka if the sendEvent method is called (may be using a different implementation)
        this.producer = this.producer == null ? new KafkaProducer<>(properties) : this.producer;

        String key = event.getKey();
        String value = new StringBuilder(event.getTransactionType().toString()).append(",").append(event.getValue()).toString();
        producer.send(new ProducerRecord<> (topic.getName(), key, value));
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }
}
