package com.djad.mes.domain.crew;

import javax.persistence.*;

@Entity
@Table(name="crew")
public class Crew {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="name")
    private String name;

    public Crew() {
    }

    public Crew(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
