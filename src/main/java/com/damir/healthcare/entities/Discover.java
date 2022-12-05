package com.damir.healthcare.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "discover")
public class Discover {
    @EmbeddedId
    public DiscoverID id;

    @Column(name = "first_enc_date", nullable = false)
    private LocalDate firstEncDate;

    public String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Discover() {
        this.id=new DiscoverID();
    }

    @Override
    public String toString() {
        return "Discover{" +
                "id=" + id +
                ", firstEncDate=" + firstEncDate +
                ", date='" + date + '\'' +
                '}';
    }

    public String getCname() {
        return id.getCname();
    }

    public void setCname(String cname) {
        this.id.setCname(cname);
    }

    public LocalDate getFirstEncDate() {
        return firstEncDate;
    }

    public void setFirstEncDate(LocalDate firstEncDate) {
        this.firstEncDate=firstEncDate;
    }
    public String getDisease_code() {
        return id.disease_code;
    }

    public void setDisease_code(String disease_code) {
        this.id.disease_code = disease_code;
    }
//    public DiscoverID getId() {
//        return id;
//    }
//
//    public void setId(DiscoverID id) {
//        this.id = id;
//    }
}
