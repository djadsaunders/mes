package com.djad.mes.events;


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
        RESET_DATA,
        RAW
    }

    private String key;
    private TransactionTypes transactionType;
    private String value;

    public Event(String key, TransactionTypes transactionType, String value) {
        this.key = key;
        this.transactionType = transactionType;
        this.value = value;
    }

    public Event(TransactionTypes transactionType) {
        this(null, transactionType, null);
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
        return "Event [resource:" + this.getKey() + ",type:" + this.getTransactionType() + ",value:" +
                this.getValue() + "]";
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
