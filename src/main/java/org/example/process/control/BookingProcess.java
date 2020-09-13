package org.example.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.example.gui.ui.MyUI;
import org.example.gui.windows.ConfirmationWindow;
import org.example.model.dao.BuchungDAO;
import org.example.model.factories.BookingFactory;
import org.example.model.objects.dto.BookingDetail;
import org.example.model.objects.dto.BookingRequest;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Booking;

import java.util.List;

public class BookingProcess {

    public static BookingProcess process = null;

    private BookingProcess(){

    }

    public static BookingProcess getInstance(){
        if(process == null){
            process = new BookingProcess();
        }
        return process;
    }

    public void createBooking(BookingRequest request, Window window){
        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);

        User user = ((MyUI) UI.getCurrent()).getUser();
        Booking booking = BookingFactory.createBooking(request, user);

        boolean result = BuchungDAO.getInstance().addBooking(booking);

        //Navigation auf Basis der (un-)erfolgreichen Buchung
        if(result){
            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Buchung erfolgreich! ID: " + booking.getId()));

        }else{
            //TODO Fehlerhandling
        }
    }

    public void deleteBookingByID(int id){
        BuchungDAO.getInstance().deleteBookingBy(id);
        UI.getCurrent().addWindow(new ConfirmationWindow("Die Reise wurde storniert!"));
    }

    public List<BookingDetail> getAllBookingsForUser(){
        //alternativ über session objekt
        final User user = ((MyUI) UI.getCurrent()).getUser();
        return BuchungDAO.getInstance().getAllBookingsForUser(user);
    }



}
