package org.example.model.objects.entities;

import org.example.model.objects.dto.Auto;
import org.example.model.objects.dto.User;

import java.time.LocalDate;

public class Reservieren {

    private int id;
    private LocalDate anreise = null;
    private LocalDate abreise = null;
    private LocalDate datumBuchung = null;
    private String iban = null;
    private int number;
    private Auto auto;
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getAnreise() {
        return anreise;
    }

    public void setAnreise(LocalDate anreise) {
        this.anreise = anreise;
    }

    public LocalDate getAbreise() {
        return abreise;
    }

    public void setAbreise(LocalDate abreise) {
        this.abreise = abreise;
    }

    public LocalDate getDatumBuchung() {
        return datumBuchung;
    }

    public void setDatumBuchung(LocalDate datumBuchung) {
        this.datumBuchung = datumBuchung;
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

    public Auto getHotel() {
        return auto;
    }

    public void setHotel(Auto auto) {
        this.auto = auto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
