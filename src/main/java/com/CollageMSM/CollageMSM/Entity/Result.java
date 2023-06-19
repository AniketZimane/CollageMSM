package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

@Entity
public class Result
{
    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "result_seq")
            @SequenceGenerator(name = "result_seq")
    Integer id;
    String studId;
    String motherName;
    String marathi;
    String hindi;
    String english;
    String math;
    String science;
    String socialsci;

    public Result() {
    }

    public Result(String studId, String motherName, String marathi, String hindi, String english, String math, String science, String socialsci) {
        this.studId = studId;
        this.motherName = motherName;
        this.marathi = marathi;
        this.hindi = hindi;
        this.english = english;
        this.math = math;
        this.science = science;
        this.socialsci = socialsci;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStudId() {
        return studId;
    }

    public void setStudId(String studId) {
        this.studId = studId;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getMarathi() {
        return marathi;
    }

    public void setMarathi(String marathi) {
        this.marathi = marathi;
    }

    public String getHindi() {
        return hindi;
    }

    public void setHindi(String hindi) {
        this.hindi = hindi;
    }

    public String getEnglish() {
        return english;
    }

    public void setEnglish(String english) {
        this.english = english;
    }

    public String getMath() {
        return math;
    }

    public void setMath(String math) {
        this.math = math;
    }

    public String getScience() {
        return science;
    }

    public void setScience(String science) {
        this.science = science;
    }

    public String getSocialsci() {
        return socialsci;
    }

    public void setSocialsci(String socialsci) {
        this.socialsci = socialsci;
    }

    @Override
    public String toString() {
        return "Result{" +
                "id=" + id +
                ", studId='" + studId + '\'' +
                ", motherName='" + motherName + '\'' +
                ", marathi='" + marathi + '\'' +
                ", hindi='" + hindi + '\'' +
                ", english='" + english + '\'' +
                ", math='" + math + '\'' +
                ", science='" + science + '\'' +
                ", socialsci='" + socialsci + '\'' +
                '}';
    }
}
