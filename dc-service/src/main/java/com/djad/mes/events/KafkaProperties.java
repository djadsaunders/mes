package com.djad.mes.events;

import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProperties extends Properties {

    private static final long serialVersionUID = 1L;

    // Have to use constructor injection or properties are not ready for the
    // constructor to use
    public KafkaProperties(@Value("${kafka.bootstrap.servers}") String bootstrapServers,
                           @Value("${kafka.topic}") String topic) {

        String serializer = StringSerializer.class.getName();
        this.put("key.serializer", serializer);
        this.put("value.serializer", serializer);
        this.put("bootstrap.servers", bootstrapServers);
        this.put("mes.topic", topic);
    }
}
