package org.example.gui.windows;

import com.vaadin.shared.ui.ContentMode;
import com.vaadin.shared.ui.grid.HeightMode;
import com.vaadin.ui.*;
import com.vaadin.ui.components.grid.SingleSelectionModel;
import org.example.model.objects.dto.BookingDetail;
import org.example.process.control.BookingProcess;

import java.util.List;

public class ListBookingWindow extends Window {

    private int currentID;
    private List<BookingDetail> liste;
    BookingDetail detail;

    public ListBookingWindow(){
        super("Liste aller Buchungen"); //window caption
        center();
        VerticalLayout layout = new VerticalLayout();

        //Erzeuge Tabelle anhand des DTO BookingDetail
        Grid<BookingDetail> grid = new Grid<>();
        grid.setSizeFull();
        grid.setHeightMode(HeightMode.UNDEFINED);
        SingleSelectionModel<BookingDetail> selection = (SingleSelectionModel<BookingDetail>) grid.getSelectionModel();


        liste = BookingProcess.getInstance().getAllBookingsForUser();
        grid.removeAllColumns();
        grid.setItems(liste);

        grid.addColumn(BookingDetail::getAnreise).setCaption("Anreise");
        grid.addColumn(BookingDetail::getAbreise).setCaption("Abreise");
        grid.addColumn(BookingDetail::getDatumBuchung).setCaption("Datum der Buchung");
        grid.addColumn(BookingDetail::getHotel).setCaption("Hotel");
        grid.addColumn(BookingDetail::getId).setCaption("ID");
        grid.addColumn(BookingDetail::getUser).setCaption("User");
        layout.addComponent(grid);

        selection.addSingleSelectionListener(event -> {
             detail = event.getValue();
        });

        Button deleteButton = new Button("Storniere Reise");
        deleteButton.addClickListener(e ->{
            ListBookingWindow.this.setCurrentID(detail.getId());

            //Löschung der Buchung
            BookingProcess.getInstance().deleteBookingByID(ListBookingWindow.this.getCurrentID());

            //Lokale Löschung der Tabelle
            grid.removeAllColumns();

            //Tabellen neu laden und darstellen (sichere Variante)
            liste = BookingProcess.getInstance().getAllBookingsForUser();
            grid.setItems(liste);
            grid.addColumn(BookingDetail::getAnreise).setCaption("Anreise");
            grid.addColumn(BookingDetail::getAbreise).setCaption("Abreise");
            grid.addColumn(BookingDetail::getDatumBuchung).setCaption("Datum der Buchung");
            grid.addColumn(BookingDetail::getHotel).setCaption("Hotel");
            grid.addColumn(BookingDetail::getId).setCaption("ID");
            grid.addColumn(BookingDetail::getUser).setCaption("User");
        });

        this.setSizeFull();

        Label emptyLabel = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(emptyLabel);
        layout.addComponent(deleteButton);
        layout.setComponentAlignment(deleteButton, Alignment.MIDDLE_CENTER);

        this.setContent(layout);
    }

    public void setCurrentID(int currentID) {
        this.currentID = currentID;
    }

    public int getCurrentID() {
        return currentID;
    }
}
