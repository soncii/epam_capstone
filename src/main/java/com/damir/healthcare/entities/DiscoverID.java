package com.damir.healthcare.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.time.LocalDate;

@Embeddable
public
class DiscoverID implements Serializable {

    @Column(name = "cname", nullable = false)
    private String cname;

    @Column(name = "disease_code", nullable = false)
    @JoinColumn(name = "disease_code")
    public String disease_code;

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDisease_code() {
        return disease_code;
    }

    @Override
    public String toString() {
        return "DiscoverID{" +
                "cname='" + cname + '\'' +
                ", disease_code='" + disease_code + '\'' +
                '}';
    }

    public void setDisease_code(String disease_code) {
        this.disease_code = disease_code;
    }

    public DiscoverID() {
    }

    public DiscoverID(String cname, String disease_code) {
        this.cname = cname;
        this.disease_code = disease_code;
    }

    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }
}
