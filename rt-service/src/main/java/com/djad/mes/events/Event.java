package com.djad.mes.events;

import com.djad.mes.domain.resource.Resource;

public class Event {

    public enum TransactionTypes {
        PRODUCTION_STATE,
        AVAILABILITY,
        SHIFT,
        CREW,
        PRODUCTION_RUN,
        COUNTER_IN,
        COUNTER_OUT,
        CREATE_RESOURCE,
        REMOVE_RESOURCE,
        RESET_DATA
    }

    private Resource resource;
    private TransactionTypes transactionType;
    private String value;

    public Event(Resource resource, TransactionTypes transactionType, String value) {
        this.resource = resource;
        this.transactionType = transactionType;
        this.value = value;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public TransactionTypes getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionTypes transactionType) {
        this.transactionType = transactionType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return "Event [resource:" + this.resource.getTag() + ",type:" + this.getTransactionType() + ",value:" +
                this.getValue() + "]";
    }
}
