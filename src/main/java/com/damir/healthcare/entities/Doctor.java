package com.damir.healthcare.entities;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "doctor")
public class Doctor {

    @Id
    @JoinColumn(name="docEmail",table = "users", referencedColumnName = "email")
    public String email;
    @Column(name = "degree", nullable = false, length = 20)
    private String degree;

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }
    //TODO [JPA Buddy] generate columns from DB
}