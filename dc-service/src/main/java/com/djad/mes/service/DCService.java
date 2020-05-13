package com.djad.mes.service;

import com.djad.mes.events.Event;
import com.djad.mes.events.EventSender;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.djad.mes.events.EventSender.Topic;
import static com.djad.mes.events.Event.TransactionTypes;

@Service
public class DCService {

    private static final Logger logger = LoggerFactory.getLogger(DCService.class);

    private EventSender eventSender;

    public DCService(EventSender eventSender) {
        this.eventSender = eventSender;
    }

    public void setRunningFullSpeed(String tag) {
        logger.debug("Service: set running full speed");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.PRODUCTION_STATE, "2"), Topic.MES);
    }

    public void setRunningSlow(String tag) {
        logger.debug("Service: set running slow");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.PRODUCTION_STATE, "1"), Topic.MES);
    }

    public void stop(String tag) {
        logger.debug("Service: stop");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.PRODUCTION_STATE, "0"), Topic.MES);
    }

    public void makeAvailable(String tag) {
        logger.debug("Service: make available");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.AVAILABILITY, "1"), Topic.MES);
    }

    public void makeUnavailable(String tag) {
        logger.debug("Service: make unavailable");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.AVAILABILITY, "0"), Topic.MES);
    }

    public void changeShift(String tag, String value) {
        logger.debug("Service: change shift");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.SHIFT, value), Topic.MES);
    }

    public void changeCrew(String tag, String value) {
        logger.debug("Service: change crew");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.CREW, value), Topic.MES);
    }

    public void changeRun(String tag, String value) {
        logger.debug("Service: change run");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.PRODUCTION_RUN, value), Topic.MES);
    }

    public void logInCount(String tag, double value) {
        logger.debug("Service: log in count");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.COUNTER_IN, String.valueOf(value)), Topic.MES);
    }

    public void logOutCount(String tag, double value) {
        logger.debug("Service: log out count");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.COUNTER_OUT, String.valueOf(value)), Topic.MES);
    }

    public void createResource(String tag, String name) {
        logger.debug("Service: create resource");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.CREATE_RESOURCE, tag + "," + name), Topic.MES);
    }

    public void removeResource(String tag) {
        logger.debug("Service: remove resource");
        this.eventSender.sendEvent(new Event(tag, TransactionTypes.REMOVE_RESOURCE, tag), Topic.MES);
    }

    public void resetData() {
        logger.debug("Service: reset all data");
        this.eventSender.sendEvent(new Event(TransactionTypes.RESET_DATA), Topic.MES);
    }
}
