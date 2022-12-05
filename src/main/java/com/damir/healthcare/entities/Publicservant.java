package com.damir.healthcare.entities;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "publicservant")
public class Publicservant {
    @Id
    @JoinColumn(table = "users", referencedColumnName = "email")
    public String email;
    @Column(name = "department", nullable = false, length = 50)
    private String department;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setEmail(String id) {
    this.email=id;
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