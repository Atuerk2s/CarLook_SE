package org.example.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.model.objects.dto.Auto;
import org.example.model.objects.dto.BookingRequest;
import org.example.process.control.BookingProcess;

import java.time.LocalDate;

public class BookingWindow extends Window {

    public BookingWindow(final Auto auto){
        super("Buchung"); //Set Window Caption
        center();

        //Some basic content for window
        VerticalLayout content = new VerticalLayout();
        content.addComponent(new Label("Buchung für Hotel: " + auto.getMarke()));
        content.setMargin(true);
        setContent(content);

        final DateField dateAnreise = new DateField();
        content.addComponent(dateAnreise);
        dateAnreise.setCaption("Anreise:");
        dateAnreise.setDateFormat("yyyy-MM-dd");
        dateAnreise.setValue(LocalDate.now()); //Vaadin 8

        final DateField dateAbreise = new DateField();
        content.addComponent(dateAbreise);
        dateAbreise.setCaption("Abreise:");
        dateAbreise.setDateFormat("yyyy-MM-dd");
        dateAbreise.setValue(LocalDate.now()); //Vaadin 8

        final ComboBox personNumber = new ComboBox();
        personNumber.setCaption("Anzahl Personen:");
        content.addComponent(personNumber);
        personNumber.setItems(1, 2, 3); //In Vaadin 8 Liste übergeben oder alle Items so


        final TextField ibanFeld = new TextField();
        ibanFeld.setCaption("IBAN:");
        content.addComponent(ibanFeld);

        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        content.addComponent(emptyLabel);

        //Enable the close button
        setClosable(true);

        //Implementierung Button
        Button buchungsButton = new Button("Buche");
        buchungsButton.addClickListener(e -> {

            BookingRequest request = new BookingRequest();
            request.setAbreise(dateAbreise.getValue());
            request.setAnreise(dateAnreise.getValue());
            request.setNumber((Integer) personNumber.getValue());
            request.setIban(ibanFeld.getValue());
            request.setAuto(auto);

            BookingProcess.getInstance().createBooking(request, BookingWindow.this);
        });

        //Hinzuügen des Buttons ins Window
        content.addComponent(buchungsButton);
        content.setComponentAlignment(buchungsButton, Alignment.MIDDLE_CENTER);
    }


}
