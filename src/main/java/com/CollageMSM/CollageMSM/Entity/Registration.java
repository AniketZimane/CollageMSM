package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

@Entity
public class Registration {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "registration_seq")
    @SequenceGenerator(name="registration_seq")
    Integer id;
    String firstname;
    String lastname;
    String email;
    String mobilenumber;
    String passsword;
    String isAdmin;
    String extension;

    public Registration() {
    }

    public Registration(String firstname, String lastname, String email, String passsword, String isAdmin) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.passsword = passsword;
        this.isAdmin = isAdmin;
    }

    public Registration(String firstname, String lastname, String email, String mobilenumber, String passsword, String isAdmin, String extension) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.passsword = passsword;
        this.isAdmin = isAdmin;
        this.extension = extension;
    }

    public Registration(Integer id, String firstname, String lastname, String email, String mobilenumber, String passsword, String isAdmin, String extension) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.mobilenumber = mobilenumber;
        this.passsword = passsword;
        this.isAdmin = isAdmin;
        this.extension = extension;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(String mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public String getPasssword() {
        return passsword;
    }

    public void setPasssword(String passsword) {
        this.passsword = passsword;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    @Override
    public String toString() {
        return "Registration{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", mobilenumber='" + mobilenumber + '\'' +
                ", passsword='" + passsword + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                ", extension='" + extension + '\'' +
                '}';
    }
}
