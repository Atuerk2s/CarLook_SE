package org.example.model.factories;

import org.example.model.objects.dto.ReservierenRequest;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Reservieren;

import java.time.LocalDate;

public class ReservierenFactory {

    public static Reservieren createBooking(ReservierenRequest request, User user){
        Reservieren reservieren = new Reservieren();

        reservieren.setAbreise(request.getAbreise());
        reservieren.setAnreise(request.getAnreise());
        reservieren.setHotel(request.getAuto());
        reservieren.setIban(request.getIban());
        reservieren.setNumber(request.getNumber());

        //User gehört zu einer Buhcung (siehe ER modell)
        reservieren.setUser(user);

        //Zusätzliches Attribut
        reservieren.setDatumBuchung(LocalDate.now());

        //reservieren.setID ...wird später be Ablage in DB hinzugefügt

        return reservieren;
    }
}
