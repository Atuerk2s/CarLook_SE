package org.example.model.objects.dto;

import java.sql.Date;
import java.time.LocalDate;

public class BookingDetail {

    private int id;
    private LocalDate anreise = null;
    private LocalDate abreise = null;
    private LocalDate datumBuchung = null;
    private String iban = null;
    private int number;
    private String hotel;
    private User user;

    public BookingDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAnreise() {
        return anreise;
    }

    public void setAnreise(Date anreise) {
        this.anreise = anreise.toLocalDate();
    }

    public LocalDate getAbreise() {
        return abreise;
    }

    public void setAbreise(Date abreise) {
        this.abreise = abreise.toLocalDate();
    }

    public LocalDate getDatumBuchung() {
        return datumBuchung;
    }

    public void setDatumBuchung(Date datumBuchung) {
        this.datumBuchung = datumBuchung.toLocalDate();
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
