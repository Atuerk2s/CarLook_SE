package org.example.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.User;
import org.example.process.control.LoginControl;
import org.example.services.util.Views;

public class TopPanel extends HorizontalLayout {

    public TopPanel() {
        this.setSizeFull();

        Label headLabel = new Label("CarLook - <i>Reservierungssystem</i>", ContentMode.HTML);
        headLabel.setSizeUndefined();
        headLabel.addStyleName("mytitel");

        this.addComponent(headLabel);
        this.setComponentAlignment(headLabel, Alignment.TOP_LEFT);

        HorizontalLayout horLayout = new HorizontalLayout();

        User user = ((MyUI) UI.getCurrent()).getUser();

        String name = null;
        if(user != null){
            name = user.getName();
        }

        Label loggedLabel = new Label("Hey " + name + ", willkommen auf CarLook");
        loggedLabel.setSizeUndefined();

        horLayout.addComponent(loggedLabel);
        horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);


        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menü", null);


        item1.addItem("Suchen", VaadinIcons.SEARCH, (MenuBar.Command) selectedItem -> UI.getCurrent().getNavigator().navigateTo(Views.MAIN));
        item1.addItem("Reservierungen", VaadinIcons.CAR, (MenuBar.Command) selectedItem -> UI.getCurrent().getNavigator().navigateTo(Views.RES));
        item1.addItem("Logout", VaadinIcons.SIGN_OUT, (MenuBar.Command) selectedItem -> LoginControl.logoutUser());

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);
    }
}