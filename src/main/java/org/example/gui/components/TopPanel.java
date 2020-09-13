package org.example.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.gui.ui.MyUI;
import org.example.gui.windows.ListBookingWindow;
import org.example.model.objects.dto.User;
import org.example.process.control.LoginControl;
import org.example.services.util.Roles;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        //Name des Systems <i> für kursiv
        Label headLabel = new Label("CarLook - <i>Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel"); //useless in community

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horLayout = new HorizontalLayout();

        //User user = (User) UI.getCurrent().getSession().getAttribute(Roles.CURRENT_USER);
        User user = ((MyUI) UI.getCurrent()).getUser();

        String vorname = null;
        if(user != null){
            vorname = user.getName();
        }

        Label loggedLabel = new Label("Eingeloggt als: " + vorname );
        loggedLabel.setSizeUndefined();

        horLayout.addComponent(loggedLabel);
        horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);


        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menü", null);

        //Logout des Users
        item1.addItem("Logout", VaadinIcons.SIGN_OUT, new MenuBar.Command() {

            public void menuSelected(MenuBar.MenuItem selectedItem) {
                LoginControl.logoutUser();
            }
        });

        /*
        //Stornierung von Reisen (power user):
        if(user.hasRole(Roles.POWER_USER)) {

            item1.addItem("Cancel", VaadinIcons.UNLINK, new MenuBar.Command() {

                public void menuSelected(MenuBar.MenuItem menuItem) {
                    //TODO: Windows wird geöffnet um Buchung anzuzeigen und ggf. zu stornieren
                     ListBookingWindow window = new ListBookingWindow();
                     UI.getCurrent().addWindow(window);
                }
            });
        }

         */

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);
    }
}
