package com.damir.healthcare.entities;


import javax.persistence.*;

@Entity
@Table
public class Reviewing {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Integer id;
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
    private Integer originalId;
    @Column
    private String action;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public Reviewing() {

    }

    @Override
    public String toString() {
        return "Reviewing{" +
                "id=" + id +
                ", diseaseCode='" + diseaseCode + '\'' +
                ", pathogen='" + pathogen + '\'' +
                ", description='" + description + '\'' +
                ", accredited=" + accredited +
                ", authorEmail='" + authorEmail + '\'' +
                ", reviewerEmailOne='" + reviewerEmailOne + '\'' +
                ", reviewerEmailTwo='" + reviewerEmailTwo + '\'' +
                ", originalId=" + originalId +
                ", action='" + action + '\'' +
                '}';
    }

    public Integer getOriginalId() {
        return originalId;
    }

    public void setOriginalId(Integer originalId) {
        this.originalId = originalId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDiseaseCode() {
        return diseaseCode;
    }

    public void setDiseaseCode(String diseaseCode) {
        this.diseaseCode = diseaseCode;
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

    public void setDescription(String description) {
        this.description = description;
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
}
