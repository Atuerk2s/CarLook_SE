package org.example.model.objects.entities;

import org.example.model.objects.dto.Auto;
import org.example.model.objects.dto.User;

import java.time.LocalDate;

public class Reservieren {

    private int id;
    private LocalDate von = null;
    private LocalDate bis = null;
    private LocalDate datumBuchung = null;
    private String telefonnummer = null;
    private int number;
    private Auto auto;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getVon() {
        return von;
    }

    public void setVon(LocalDate anreise) {
        this.von = anreise;
    }

    public LocalDate getBis() {
        return bis;
    }

    public void setBis(LocalDate abreise) {
        this.bis = abreise;
    }

    public LocalDate getDatumBuchung() {
        return datumBuchung;
    }

    public void setDatumBuchung(LocalDate datumBuchung) {
        this.datumBuchung = datumBuchung;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public void setTelefonnummer(String telefonnummer) {
        this.telefonnummer = telefonnummer;
    }

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
