package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

@Entity
public class StudentsAditionalDetails
{
    @Id
            @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "StudentsAditionalDetails_seq")
            @SequenceGenerator(name = "StudentsAditionalDetails_seq")
    Integer id;
    @Column(unique = true)
    String studId;
    String scheduledCastOpn;
    String currentClass;
    String examStu;
    String subject;
    String promotionStu;
    String fees;
    String feesConcession;
    String generalConduct;
    String reason;

    public StudentsAditionalDetails() {
    }

    public StudentsAditionalDetails(String studId, String scheduledCastOpn, String currentClass, String examStu, String subject, String promotionStu, String fees, String feesConcession, String generalConduct, String reason) {
        this.studId = studId;
        this.scheduledCastOpn = scheduledCastOpn;
        this.currentClass = currentClass;
        this.examStu = examStu;
        this.subject = subject;
        this.promotionStu = promotionStu;
        this.fees = fees;
        this.feesConcession = feesConcession;
        this.generalConduct = generalConduct;
        this.reason = reason;
    }

    public StudentsAditionalDetails(String studId, String scheduledCastOpn, String currentClass, String fees, String feesConcession) {
        this.studId = studId;
        this.scheduledCastOpn = scheduledCastOpn;
        this.currentClass = currentClass;
        this.fees = fees;
        this.feesConcession = feesConcession;
    }

    public StudentsAditionalDetails(Integer id, String studId, String scheduledCastOpn, String currentClass, String examStu, String subject, String promotionStu, String fees, String feesConcession, String generalConduct, String reason) {
        this.id = id;
        this.studId = studId;
        this.scheduledCastOpn = scheduledCastOpn;
        this.currentClass = currentClass;
        this.examStu = examStu;
        this.subject = subject;
        this.promotionStu = promotionStu;
        this.fees = fees;
        this.feesConcession = feesConcession;
        this.generalConduct = generalConduct;
        this.reason = reason;
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

    public String getScheduledCastOpn() {
        return scheduledCastOpn;
    }

    public void setScheduledCastOpn(String scheduledCastOpn) {
        this.scheduledCastOpn = scheduledCastOpn;
    }

    public String getCurrentClass() {
        return currentClass;
    }

    public void setCurrentClass(String currentClass) {
        this.currentClass = currentClass;
    }

    public String getExamStu() {
        return examStu;
    }

    public void setExamStu(String examStu) {
        this.examStu = examStu;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPromotionStu() {
        return promotionStu;
    }

    public void setPromotionStu(String promotionStu) {
        this.promotionStu = promotionStu;
    }

    public String getFees() {
        return fees;
    }

    public void setFees(String fees) {
        this.fees = fees;
    }

    public String getFeesConcession() {
        return feesConcession;
    }

    public void setFeesConcession(String feesConcession) {
        this.feesConcession = feesConcession;
    }

    public String getGeneralConduct() {
        return generalConduct;
    }

    public void setGeneralConduct(String generalConduct) {
        this.generalConduct = generalConduct;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    @Override
    public String toString() {
        return "StudentsAditionalDetails{" +
                "id=" + id +
                ", studId='" + studId + '\'' +
                ", scheduledCastOpn='" + scheduledCastOpn + '\'' +
                ", currentClass='" + currentClass + '\'' +
                ", examStu='" + examStu + '\'' +
                ", subject='" + subject + '\'' +
                ", promotionStu='" + promotionStu + '\'' +
                ", fees='" + fees + '\'' +
                ", feesConcession='" + feesConcession + '\'' +
                ", generalConduct='" + generalConduct + '\'' +
                ", reason='" + reason + '\'' +
                '}';
    }
}
