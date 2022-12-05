package com.damir.healthcare.entities;

import javax.persistence.*;
import java.lang.reflect.Field;

@Entity
@Table(name = "disease")
public class Disease {

    @Column(name = "disease_code", nullable = false, length = 50)
    private String diseaseCode;

    @Column(name = "pathogen", nullable = false, length = 20)
    private String pathogen;

    @Column(name = "description", nullable = false, length = 140)
    private String description;

    @Column
    private Boolean accredited;

    @Column
    private String authorEmail;
    @Column
    private String reviewerEmailOne;
    @Column
    private String reviewerEmailTwo;

    @Column
    private String lastModifiedBy;

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id")
    private Integer id;

    public Disease(){

    }

    public Boolean getAccredited() {
        return accredited;
    }

    public void setAccredited(Boolean accredited) {
        this.accredited = accredited;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getReviewerEmailOne() {
        return reviewerEmailOne;
    }

    public void setReviewerEmailOne(String reviewerEmailOne) {
        this.reviewerEmailOne = reviewerEmailOne;
    }

    public String getReviewerEmailTwo() {
        return reviewerEmailTwo;
    }

    public void setReviewerEmailTwo(String reviewerEmailTwo) {
        this.reviewerEmailTwo = reviewerEmailTwo;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Disease{" +
                "diseaseCode='" + diseaseCode + '\'' +
                ", pathogen='" + pathogen + '\'' +
                ", description='" + description + '\'' +
                ", accredited=" + accredited +
                ", authorEmail='" + authorEmail + '\'' +
                ", reviewerEmailOne='" + reviewerEmailOne + '\'' +
                ", reviewerEmailTwo='" + reviewerEmailTwo + '\'' +
                ", lastModifiedBy='" + lastModifiedBy + '\'' +
                ", id=" + id +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isNull() throws IllegalAccessException {
        for (Field f : getClass().getDeclaredFields()) {
            if (f.get(this) != null)
                return false;
        }
        return true;
    }
}