package com.djad.mes.events;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Collection;

@Component
public class EventConsumerDelegate {
    private Consumer consumer;

    public EventConsumerDelegate(KafkaProperties properties) {
        this.consumer = new KafkaConsumer(properties);
    }

    public void subscribe(Collection<String> topics) {
        this.consumer.subscribe(topics);
    }

    public ConsumerRecords<String,String> poll(Duration duration) {
        return this.consumer.poll(duration);
    }

    public void closeConsumer() {
        this.consumer.close();
    }
}
