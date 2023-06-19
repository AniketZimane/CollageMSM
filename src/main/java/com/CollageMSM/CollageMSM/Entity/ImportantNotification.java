package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

@Entity
public class ImportantNotification
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "importantdate_sequence")
    @SequenceGenerator(name="importantdate_seq")
    Integer id;
    String title;
    String date;

    public ImportantNotification() {
    }

    public ImportantNotification(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public ImportantNotification(Integer id, String title, String date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ImportantNotification{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
