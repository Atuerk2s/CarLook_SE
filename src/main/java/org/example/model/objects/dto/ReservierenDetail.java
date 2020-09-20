package org.example.model.objects.dto;

import java.sql.Date;
import java.time.LocalDate;

public class ReservierenDetail {

    private int id;
    private LocalDate von = null;
    private LocalDate bis = null;
    private LocalDate datumReservierung = null;
    private String telefonnummer = null;
    private String Auto;
    private User user;

    public ReservierenDetail() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getVon() {
        return von;
    }

    public void setVon(Date von) {
        this.von = von.toLocalDate();
    }

    public LocalDate getBis() {
        return bis;
    }

    public void setBis(Date bis) {
        this.bis = bis.toLocalDate();
    }

    public LocalDate getDatumReservierung() {
        return datumReservierung;
    }

    public void setDatumReservierung(Date datumReservierung) {
        this.datumReservierung = datumReservierung.toLocalDate();
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public String getAuto() { return Auto; }

    public void setAuto(String hotel) {
        this.Auto = hotel;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
