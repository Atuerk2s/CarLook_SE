package org.example.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.model.objects.dto.Auto;
import org.example.model.objects.dto.ReservierenRequest;
import org.example.process.control.ReservierungProcess;

import java.time.LocalDate;

public class ReservierungWindow extends Window {

    public ReservierungWindow(final Auto auto){
        super("Reservierung"); //Set Window Caption
        center();

        //Some basic content for window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Reservierung des Autos: " + auto.getMarke()));
        content.setMargin(true);
        setContent(content);

        final DateField dateVon = new DateField();
        content.addComponent(dateVon);
        dateVon.setCaption("Von:");
        dateVon.setDateFormat("yyyy-MM-dd");
        dateVon.setValue(LocalDate.now()); //Vaadin 8

        final DateField dateBis = new DateField();
        content.addComponent(dateBis);
        dateBis.setCaption("Bis:");
        dateBis.setDateFormat("yyyy-MM-dd");
        dateBis.setValue(LocalDate.now()); //Vaadin 8

        final TextField telefonnummerFeld = new TextField();
        telefonnummerFeld.setCaption("Telefonnummer:");
        content.addComponent(telefonnummerFeld);

        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        content.addComponent(emptyLabel);

        //Enable the close button
        setClosable(true);

        //Implementierung Button
        Button reservButton = new Button("Reservieren");
        reservButton.addClickListener(e -> {

            ReservierenRequest request = new ReservierenRequest();
            request.setBis(dateBis.getValue());
            request.setVon(dateVon.getValue());
            request.setTelefonnummer(telefonnummerFeld.getValue());
            request.setAuto(auto);

            ReservierungProcess.getInstance().createBooking(request, ReservierungWindow.this);
        });

        //Hinzufügen des Buttons ins Window
        content.addComponent(reservButton);
        content.setComponentAlignment(reservButton, Alignment.MIDDLE_CENTER);
    }


}
