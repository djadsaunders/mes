package com.djad.mes.events;

import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class KafkaProperties extends Properties {

    private static final long serialVersionUID = 1L;

    // Have to use constructor injection or properties are not ready for the
    // constructor to use
    public KafkaProperties(@Value("${kafka.bootstrap.servers}") String bootstrapServers) {

        String deserializer = StringDeserializer.class.getName();

        this.put("group.id", "mes-consumer-1");
        this.put("enable.auto.commit", "true");
        this.put("auto.commit.interval.ms", "1000");
        this.put("auto.offset.reset", "latest");
        this.put("session.timeout.ms", "30000");
        this.put("key.deserializer", deserializer);
        this.put("value.deserializer", deserializer);
        this.put("bootstrap.servers", bootstrapServers);
    }
}
