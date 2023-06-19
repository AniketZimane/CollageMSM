package com.CollageMSM.CollageMSM.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class Admission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admission_sequence")
    @SequenceGenerator(name = "admission_seq")
    Integer id;
    String stuclass;
    String fname;
    String mname;
    String lname;
    String motherName;
    String dob;
    String emailid;
    String lastschoolName;
    String mobileno;
    String country;
    String state;
    String district;
    String hobbies;
    String age;
    String percentage;
    String extension;
    String skills;
    String fqualification;
    String mqualification;
    String cast;
    String gender;
    String pnumber;
    String peraddress;
    String tempaddress;
    String status;
    @CreationTimestamp
    LocalDate admissionDate;
    private Integer currentYear;

    @PrePersist
    private void prePersist() {
        currentYear = LocalDate.now().getYear();
    }

    public Admission() {
    }

    public Admission(String stuclass, String fname, String mname, String lname, String motherName, String dob,
            String emailid, String lastschoolName, String mobileno, String country, String state, String district,
            String hobbies, String age, String percentage, String extension, String skills, String fqualification,
            String mqualification, String cast, String gender, String pnumber, String peraddress, String tempaddress,
            String status, LocalDate admissionDate, Integer currentYear) {
        this.stuclass = stuclass;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.motherName = motherName;
        this.dob = dob;
        this.emailid = emailid;
        this.lastschoolName = lastschoolName;
        this.mobileno = mobileno;
        this.country = country;
        this.state = state;
        this.district = district;
        this.hobbies = hobbies;
        this.age = age;
        this.percentage = percentage;
        this.extension = extension;
        this.skills = skills;
        this.fqualification = fqualification;
        this.mqualification = mqualification;
        this.cast = cast;
        this.gender = gender;
        this.pnumber = pnumber;
        this.peraddress = peraddress;
        this.tempaddress = tempaddress;
        this.status = status;
        this.admissionDate = admissionDate;
        this.currentYear = LocalDate.now().getYear();
    }

    public Admission(Integer id, String stuclass, String fname, String mname, String lname, String motherName,
            String dob, String emailid, String lastschoolName, String mobileno, String country, String state,
            String district, String hobbies, String age, String percentage, String extension, String skills,
            String fqualification, String mqualification, String cast, String gender, String pnumber, String peraddress,
            String tempaddress, String status, LocalDate admissionDate, Integer currentYear) {
        this.id = id;
        this.stuclass = stuclass;
        this.fname = fname;
        this.mname = mname;
        this.lname = lname;
        this.motherName = motherName;
        this.dob = dob;
        this.emailid = emailid;
        this.lastschoolName = lastschoolName;
        this.mobileno = mobileno;
        this.country = country;
        this.state = state;
        this.district = district;
        this.hobbies = hobbies;
        this.age = age;
        this.percentage = percentage;
        this.extension = extension;
        this.skills = skills;
        this.fqualification = fqualification;
        this.mqualification = mqualification;
        this.cast = cast;
        this.gender = gender;
        this.pnumber = pnumber;
        this.peraddress = peraddress;
        this.tempaddress = tempaddress;
        this.status = status;
        this.admissionDate = admissionDate;
        this.currentYear = LocalDate.now().getYear();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStuclass() {
        return stuclass;
    }

    public void setStuclass(String stuclass) {
        this.stuclass = stuclass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmailid() {
        return emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

    public String getLastschoolName() {
        return lastschoolName;
    }

    public void setLastschoolName(String lastschoolName) {
        this.lastschoolName = lastschoolName;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getHobbies() {
        return hobbies;
    }

    public void setHobbies(String hobbies) {
        this.hobbies = hobbies;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getFqualification() {
        return fqualification;
    }

    public void setFqualification(String fqualification) {
        this.fqualification = fqualification;
    }

    public String getMqualification() {
        return mqualification;
    }

    public void setMqualification(String mqualification) {
        this.mqualification = mqualification;
    }

    public String getCast() {
        return cast;
    }

    public void setCast(String cast) {
        this.cast = cast;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPnumber() {
        return pnumber;
    }

    public void setPnumber(String pnumber) {
        this.pnumber = pnumber;
    }

    public String getPeraddress() {
        return peraddress;
    }

    public void setPeraddress(String peraddress) {
        this.peraddress = peraddress;
    }

    public String getTempaddress() {
        return tempaddress;
    }

    public void setTempaddress(String tempaddress) {
        this.tempaddress = tempaddress;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getAdmissionDate() {
        return admissionDate;
    }

    public void setAdmissionDate(LocalDate admissionDate) {
        this.admissionDate = admissionDate;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public String toString() {
        return "Admission{" +
                "id=" + id +
                ", stuclass='" + stuclass + '\'' +
                ", fname='" + fname + '\'' +
                ", mname='" + mname + '\'' +
                ", lname='" + lname + '\'' +
                ", motherName='" + motherName + '\'' +
                ", dob='" + dob + '\'' +
                ", emailid='" + emailid + '\'' +
                ", lastschoolName='" + lastschoolName + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", district='" + district + '\'' +
                ", hobbies='" + hobbies + '\'' +
                ", age='" + age + '\'' +
                ", percentage='" + percentage + '\'' +
                ", extension='" + extension + '\'' +
                ", skills='" + skills + '\'' +
                ", fqualification='" + fqualification + '\'' +
                ", mqualification='" + mqualification + '\'' +
                ", cast='" + cast + '\'' +
                ", gender='" + gender + '\'' +
                ", pnumber='" + pnumber + '\'' +
                ", peraddress='" + peraddress + '\'' +
                ", tempaddress='" + tempaddress + '\'' +
                ", status='" + status + '\'' +
                ", currentYear=" + currentYear +
                ", admissionDate=" + admissionDate +
                '}';
    }
}
