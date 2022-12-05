package com.damir.healthcare.entities;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "record")
public class Record {
    @EmbeddedId
    private RecordID id;
    @Column(name = "total_deaths", nullable = false)
    private Integer totalDeaths;

    @Column(name = "total_patients", nullable = false)
    private Integer totalPatients;

    public void setId(RecordID id) {
        this.id = id;
    }
    public Record() {
        this.id=new RecordID();
    }
    public Record(RecordID id) {
        this.id=id;
    }

    public String getEmail() {
        return id.getEmail();
    }

    public void setEmail(String email) {
        this.id.setEmail(email);
    }

    public String getCname() {
        return id.getCname();
    }

    public void setCname(String  cname) {
        this.id.setCname(cname);
    }

    public String getDiseaseCode() {
        return id.getDiseaseCode();
    }

    public void setDiseaseCode(String  diseaseCode) {
        this.id.setDiseaseCode(diseaseCode);
    }

    public Integer getTotalDeaths() {
        return totalDeaths;
    }

    public void setTotalDeaths(Integer totalDeaths) {
        this.totalDeaths = totalDeaths;
    }

    public Integer getTotalPatients() {
        return totalPatients;
    }

    public void setTotalPatients(Integer totalPatients) {
        this.totalPatients = totalPatients;
    }
    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }
}
