package org.example.process.control;

import com.vaadin.ui.UI;
import com.vaadin.ui.Window;
import org.example.gui.ui.MyUI;
import org.example.gui.windows.ConfirmationWindow;
import org.example.model.dao.ReservierungDAO;
import org.example.model.factories.ReservierenFactory;
import org.example.model.objects.dto.ReservierenDetail;
import org.example.model.objects.dto.ReservierenRequest;
import org.example.model.objects.dto.User;
import org.example.model.objects.entities.Reservieren;

import java.util.List;

public class ReservierungProcess {

    public static ReservierungProcess process = null;

    private ReservierungProcess(){

    }

    public static ReservierungProcess getInstance(){
        if(process == null){
            process = new ReservierungProcess();
        }
        return process;
    }

    public void createBooking(ReservierenRequest request, Window window){


        User user = ((MyUI) UI.getCurrent()).getUser();
        Reservieren reservieren = ReservierenFactory.createBooking(request, user);

        boolean result = ReservierungDAO.getInstance().addReservierung(reservieren);

        if(result){
            window.close();
            UI.getCurrent().addWindow(new ConfirmationWindow("Reservierung erfolgreich! ID: " + reservieren.getId()));

        }else{
            //TODO Fehlerhandling
        }
    }


    public List<ReservierenDetail> getAllBookingsForUser(){

        final User user = ((MyUI) UI.getCurrent()).getUser();
        return ReservierungDAO.getInstance().getAllBookingsForUser(user);
    }



}
