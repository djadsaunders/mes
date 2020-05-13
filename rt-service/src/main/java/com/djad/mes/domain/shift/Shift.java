package com.djad.mes.domain.shift;

import javax.persistence.*;

@Entity
@Table(name="shift")
public class Shift {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    public Shift() {
    }

    public Shift(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
