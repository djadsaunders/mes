package com.djad.mes.domain.product;

import javax.persistence.*;

@Entity
@Table(name="production_run")
public class ProductionRun {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    private Product product;

    @Column(name="name")
    private String name;

    public ProductionRun(Product product, String name) {
        this.product = product;
        this.name = name;
    }

    public ProductionRun() {}

    public String getName() {
        return this.name;
    }
}
