package com.djad.mes.domain.resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="availability_period")
public class AvailabilityPeriod {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="from_date")
    private Date from;

    @Column(name="to_date")
    private Date to;

    @Column(name="availability")
    @Enumerated(EnumType.ORDINAL)
    private Availability availability;

    @ManyToOne
    private Resource resource;

    public AvailabilityPeriod(Availability availabilityState, Resource resource) {
        this.from = new Date();
        this.availability = availabilityState;
        this.resource = resource;
    }

    public AvailabilityPeriod() {}

    public boolean isAvailable() {
        return this.availability == Availability.AVAILABLE;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
}
