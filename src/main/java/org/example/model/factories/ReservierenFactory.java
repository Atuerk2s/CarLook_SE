package org.example.model.factories;

import org.example.model.objects.dto.ReservierenRequest;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Reservieren;

import java.time.LocalDate;

public class ReservierenFactory {

    public static Reservieren createBooking(ReservierenRequest request, User user){
        Reservieren reservieren = new Reservieren();

        reservieren.setBis(request.getBis());
        reservieren.setVon(request.getVon());
        reservieren.setAuto(request.getAuto());
        reservieren.setTelefonnummer(request.getTelefonnummer());

        //User gehört zu einer Reservierung (siehe ER modell)
        reservieren.setUser(user);

        //Zusätzliches Attribut
        reservieren.setDatumBuchung(LocalDate.now());

        return reservieren;
    }
}
