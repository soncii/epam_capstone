package com.damir.healthcare.entities;

import javax.persistence.*;
import java.io.Serializable;

@Embeddable
public class RecordID implements Serializable {
    private static final long serialVersionUID = -7272069350466084674L;

    public RecordID(String email) {
        this.email = email;
    }

    @JoinColumn(table = "publicservant", referencedColumnName = "email")
    private String  email;
    @Column(table="country", name = "cname")
    private String cname;

    @Column(table = "disease",name = "disease_code")
    private String diseaseCode;

    public RecordID() {

    }

    public RecordID(String email, String cname, String diseaseCode) {
        this.email = email;
        this.cname = cname;
        this.diseaseCode = diseaseCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }



}
