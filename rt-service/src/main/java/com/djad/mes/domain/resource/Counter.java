package com.djad.mes.domain.resource;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="counter")
public class Counter {
    public enum CounterTypes {
        IN,
        OUT
    }

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name="count_date")
    private Date countDate;

    @Column(name="value")
    private double value;

    @Column(name="counter_type")
    private String counterType;

    @ManyToOne
    Resource resource;

    public Counter() {}

    public Counter(CounterTypes type, Resource resource, double value) {
        this.countDate = new Date();
        this.value = value;
        this.counterType = type.toString();
        this.resource = resource;
    }

    public Date getCountDate() {
        return countDate;
    }

    public void setCountDate(Date fromDate) {
        this.countDate = fromDate;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getCounterType() {
        return counterType;
    }

    public void setCounterType(CounterTypes type) {
        this.counterType = type.toString();
    }
}
