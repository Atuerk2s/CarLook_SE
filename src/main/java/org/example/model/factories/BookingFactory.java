package org.example.model.factories;

import org.example.model.objects.dto.BookingRequest;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Booking;

import java.time.LocalDate;

public class BookingFactory {

    public static Booking createBooking(BookingRequest request, User user){
        Booking book = new Booking();

        book.setAbreise(request.getAbreise());
        book.setAnreise(request.getAnreise());
        book.setHotel(request.getAuto());
        book.setIban(request.getIban());
        book.setNumber(request.getNumber());

        //User gehört zu einer Buhcung (siehe ER modell)
        book.setUser(user);

        //Zusätzliches Attribut
        book.setDatumBuchung(LocalDate.now());

        //book.setID ...wird später be Ablage in DB hinzugefügt

        return book;
    }
}
