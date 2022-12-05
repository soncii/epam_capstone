package com.damir.healthcare.entities;

import com.damir.healthcare.entities.Country;

import javax.management.relation.Role;
import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "email", nullable = false, length = 60)
    private String id;

    private String password;


    private String name;


    private String surname;


    private Integer salary;


    private String phone;



    private String cname;

    public User() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }


}