package com.CollageMSM.CollageMSM.Entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SeatReservation
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "seatReservaton_seq")
    @SequenceGenerator(name = "seatReservaton_seq")
    Integer id;
    String totalSeat;
    String openCat;
    String obcCat;
    String vjntCat;
    String stCat;
    String ewsCat;
    String tfwsCat;
    @Column(unique = true)
    private Integer currentYear;

    public SeatReservation(String openCat, String obcCat, String vjntCat, String stCat, String ewsCat, String tfwsCat) {
    }


    @PrePersist
    private void prePersist() {
        currentYear = LocalDate.now().getYear();
    }

    public SeatReservation() {
    }
    public SeatReservation(Integer id, String openCat, String obcCat, String vjntCat, String stCat, String ewsCat, String tfwsCat, Integer currentYear) {
        this.id = id;
        this.openCat = openCat;
        this.obcCat = obcCat;
        this.vjntCat = vjntCat;
        this.stCat = stCat;
        this.ewsCat = ewsCat;
        this.tfwsCat = tfwsCat;
        this.currentYear = currentYear;
    }
    public SeatReservation(String openCat, String obcCat, String vjntCat, String stCat, String ewsCat, String tfwsCat, Integer currentYear) {
        this.openCat = openCat;
        this.obcCat = obcCat;
        this.vjntCat = vjntCat;
        this.stCat = stCat;
        this.ewsCat = ewsCat;
        this.tfwsCat = tfwsCat;
        this.currentYear = currentYear;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(String totalSeat) {
        this.totalSeat = totalSeat;
    }

    public String getOpenCat() {
        return openCat;
    }

    public void setOpenCat(String openCat) {
        this.openCat = openCat;
    }

    public String getObcCat() {
        return obcCat;
    }

    public void setObcCat(String obcCat) {
        this.obcCat = obcCat;
    }

    public String getVjntCat() {
        return vjntCat;
    }

    public void setVjntCat(String vjntCat) {
        this.vjntCat = vjntCat;
    }

    public String getStCat() {
        return stCat;
    }

    public void setStCat(String stCat) {
        this.stCat = stCat;
    }

    public String getEwsCat() {
        return ewsCat;
    }

    public void setEwsCat(String ewsCat) {
        this.ewsCat = ewsCat;
    }

    public String getTfwsCat() {
        return tfwsCat;
    }

    public void setTfwsCat(String tfwsCat) {
        this.tfwsCat = tfwsCat;
    }

    public Integer getCurrentYear() {
        return currentYear;
    }

    public void setCurrentYear(Integer currentYear) {
        this.currentYear = currentYear;
    }

    @Override
    public String toString() {
        return "SeatReservation{" +
                "id=" + id +
                ", totalSeat='" + totalSeat + '\'' +
                ", openCat='" + openCat + '\'' +
                ", obcCat='" + obcCat + '\'' +
                ", vjntCat='" + vjntCat + '\'' +
                ", stCat='" + stCat + '\'' +
                ", ewsCat='" + ewsCat + '\'' +
                ", tfwsCat='" + tfwsCat + '\'' +
                ", currentYear=" + currentYear +
                '}';
    }
}
