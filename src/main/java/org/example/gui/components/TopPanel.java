package org.example.gui.components;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.User;
import org.example.process.control.LoginControl;

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

        Label loggedLabel = new Label("Hey " + vorname + ", willkommen auf CarLook");
        loggedLabel.setSizeUndefined();

        horLayout.addComponent(loggedLabel);
        horLayout.setComponentAlignment(loggedLabel, Alignment.MIDDLE_CENTER);


        MenuBar bar = new MenuBar();
        MenuBar.MenuItem item1 = bar.addItem("Menü", null);

        //Logout des Users
        item1.addItem("Logout", VaadinIcons.SIGN_OUT, (MenuBar.Command) selectedItem -> LoginControl.logoutUser());

        horLayout.addComponent(bar);
        this.addComponent(horLayout);
        this.setComponentAlignment(horLayout, Alignment.TOP_RIGHT);
    }
}