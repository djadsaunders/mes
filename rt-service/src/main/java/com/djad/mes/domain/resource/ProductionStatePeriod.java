package com.djad.mes.domain.resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="production_state_period")
public class ProductionStatePeriod {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="from_date")
    private Date from;

    @Column(name="to_date")
    private Date to;

    @ManyToOne
    private Resource resource;

    @Column(name="state")
    @Enumerated(EnumType.ORDINAL)
    private ProductionState productionState;

    public ProductionStatePeriod(ProductionState productionState, Resource resource) {
        this.from = new Date();
        this.productionState = productionState;
        this.resource = resource;
    }

    public ProductionStatePeriod() {
    }

    public boolean isRunning() {
        return productionState == ProductionState.SLOW || productionState == ProductionState.RUNNING;
    }

    public boolean isRunningFullSpeed() {
        return productionState == ProductionState.RUNNING;
    }

    public boolean isRunningSlow() {
        return productionState == ProductionState.SLOW;
    }

    public boolean isStopped() {
        return productionState == ProductionState.STOPPED;
    }

    public ProductionState getProductionState() {
        return this.productionState;
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
