package com.damir.healthcare.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.lang.reflect.Field;

@Entity
@Table(name = "country")
public class Country {
    @Id
    @Column(name = "cname", nullable = false, length = 50)
    private String id;

    @Column(name = "population", nullable = false)
    private Long population;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPopulation() {
        return population;
    }

    public void setPopulation(Long population) {
        this.population = population;
    }
    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }

}