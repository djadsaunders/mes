package com.djad.mes.domain.crew;


import com.djad.mes.domain.resource.Resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="crew_period")
public class CrewPeriod {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="from_date")
    private Date from;

    @Column(name="to_date")
    private Date to;

    @ManyToOne
    private Crew crew;

    @OneToOne
    private Resource resource;

    public CrewPeriod(Crew crew, Resource resource) {
        this.from = new Date();
        this.crew = crew;
        this.resource = resource;
    }

    public CrewPeriod() {}

    public Crew getCrew() {
        return this.crew;
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
