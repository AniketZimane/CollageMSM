package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

@Entity
public class BonafideRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bonafidereques_seq")
    @SequenceGenerator(name = "bonafidereques_seq")
    Integer id;
    Long studentId;
    String firstname;
    String lastname;
    String email;
    String stuclass;
    String extension;
    String reason;
    String status;

    public BonafideRequest() {
    }

    public BonafideRequest(Long studentId, String firstname, String lastname, String email, String stuclass,
            String extension, String reason, String status) {
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.stuclass = stuclass;
        this.extension = extension;
        this.reason = reason;
        this.status = status;
    }

    public BonafideRequest(Integer id, Long studentId, String firstname, String lastname, String email, String stuclass,
            String extension, String reason, String status) {
        this.id = id;
        this.studentId = studentId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.stuclass = stuclass;
        this.extension = extension;
        this.reason = reason;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "BonafideRequest{" +
                "id=" + id +
                ", studentId=" + studentId +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", stuclass='" + stuclass + '\'' +
                ", extension='" + extension + '\'' +
                ", reason='" + reason + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
