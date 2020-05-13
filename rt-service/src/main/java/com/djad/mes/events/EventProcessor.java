package com.djad.mes.events;

import com.djad.mes.MesApplicationRuntimeException;
import com.djad.mes.service.ResourceService;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Listens to the event stream and processes events.
 * When the bean is created, consumeEvents() is automatically called to start processing from the queue.
 */
@Component
public class EventProcessor {
    private static final Logger logger = LoggerFactory.getLogger(EventProcessor.class);

    private EventConsumerDelegate eventConsumer;
    private ResourceService resourceService;
    private String topic;

    @Autowired
    public EventProcessor(ResourceService resourceService,  
                          EventConsumerDelegate consumer, @Value("${kafka.topic}") String topic) {
        this.resourceService = resourceService;
        this.eventConsumer = consumer;
        this.topic = topic;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void consumeEvents() {
        logger.debug("Starting consume events..");

        eventConsumer.subscribe(Arrays.asList(topic));

        ExecutorService executorService = Executors.newSingleThreadExecutor();

        executorService.execute(() -> {
            while (true) {
                ConsumerRecords<String, String> records = eventConsumer.poll(Duration.ofSeconds(1));
                for (ConsumerRecord<String, String> record : records) {
                    try {
                        this.consumeSingleEvent(record.key(), record.value());
                    } catch (Exception e) {
                        logger.debug("Failed to process event: ", e);
                    }
                }
            }
        });
    }

    public void consumeSingleEvent(Object key, Object value) {
        logger.debug("Processing: key=" + key + ", value=" + value);

        if (value == null) throw new MesApplicationRuntimeException("Value was null");

        String[] eventParts = value.toString().split(",");

        Event.TransactionTypes eventType = Event.TransactionTypes.valueOf(eventParts[0]);

        switch (eventType) {
            case AVAILABILITY:
                if (eventParts[1].equals("1")) {
                    resourceService.makeAvailable(key.toString());
                }
                else {
                    resourceService.makeUnavailable(key.toString());
                }
                break;
            case CREW:
                resourceService.changeCrew(key.toString(), eventParts[1]);
                break;
            case SHIFT:
                resourceService.changeShift(key.toString(), eventParts[1]);
                break;
            case PRODUCTION_RUN:
                resourceService.changeProductionRun(key.toString(), eventParts[1]);
                break;
            case PRODUCTION_STATE:
                if (eventParts[1].equals("0")) {
                    resourceService.stop(key.toString());
                }
                else if (eventParts[1].equals("1")) {
                    resourceService.setRunningSlow(key.toString());
                }
                else {
                    resourceService.setRunningFullSpeed(key.toString());
                }
                break;
            case COUNTER_IN:
                resourceService.logInCount(key.toString(), Double.valueOf(eventParts[1]));
                break;
            case COUNTER_OUT:
                resourceService.logOutCount(key.toString(), Double.valueOf(eventParts[1]));
                break;
            case CREATE_RESOURCE:
                resourceService.createResource(key.toString(), eventParts[2]);
                break;
            default:
                logger.error("Did not recognize event type: " + eventType);
                break;
        }
    }

    @Override
    public void finalize() {
        eventConsumer.closeConsumer();
    }
}
