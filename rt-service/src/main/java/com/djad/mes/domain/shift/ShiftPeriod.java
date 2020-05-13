package com.djad.mes.domain.shift;

import com.djad.mes.domain.resource.Resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="shift_period")
public class ShiftPeriod {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="from_date")
    private Date from;

    @Column(name="to_date")
    private Date to;

    @ManyToOne
    private Shift shift;

    @OneToOne
    private Resource resource;

    public ShiftPeriod(Shift shift, Resource resource) {
        this.shift = shift;
        this.resource = resource;
        this.from = new Date();
    }

    public ShiftPeriod() {}

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

    public Shift getShift() {
        return shift;
    }

    public void setShift(Shift shift) {
        this.shift = shift;
    }
}
