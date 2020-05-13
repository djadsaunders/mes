package com.djad.mes.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.djad.mes.events.EventSender;
import com.djad.mes.events.Event;

import static com.djad.mes.events.EventSender.Topic;
import static com.djad.mes.events.Event.TransactionTypes;

@Service
public class SignalService {

    private static final Logger logger = LoggerFactory.getLogger(SignalService.class);

    private EventSender eventSender;

    public SignalService(EventSender eventSender) {
        this.eventSender = eventSender;
    }

    public void processSignal(String deviceId, String sensorId, String value) {
        logger.debug("SignalService: process signal (deviceId=" + deviceId + ", sensorId=" + sensorId + ", value=" + value + ")");
        this.eventSender.sendEvent(new Event(deviceId + "\\" + sensorId, TransactionTypes.RAW, value), Topic.RAW);
    }
}
