package org.example.model.objects.dto;

import java.time.LocalDate;

public class ReservierenRequest {

    private LocalDate von = null;
    private LocalDate bis = null;
    private String telefonnummer = null;
    private Auto auto;


    public LocalDate getVon() {
        return von;
    }

    public void setVon(LocalDate von) {
        this.von = von;
    }

    public LocalDate getBis() { return bis; }

    public void setBis(LocalDate bis) {
        this.bis = bis;
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
}
