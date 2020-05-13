package com.djad.mes.domain.product;

import com.djad.mes.domain.resource.Resource;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="production_run_period")
public class ProductionRunPeriod {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="from_date")
    private Date from;

    @Column(name="to_date")
    private Date to;

    @Column(name="value")
    private String value;

    @ManyToOne
    private Resource resource;

    @OneToOne
    private ProductionRun productionRun;

    public ProductionRunPeriod(ProductionRun productionRun, Resource resource) {
        this.from = new Date();
        this.productionRun = productionRun;
        this.resource = resource;
    }

    public ProductionRunPeriod() {}

    public ProductionRun getProductionRun() {
        return this.productionRun;
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

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
