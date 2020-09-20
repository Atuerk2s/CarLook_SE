package org.example.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.server.FontAwesome;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.User;
import org.example.process.control.LoginControl;
import org.example.process.control.RegistrationControl;
import org.example.process.control.exceptions.DatabaseException;
import org.example.process.control.exceptions.EmailException;
import org.example.process.control.exceptions.NoSuchUserOrPassword;
import org.example.services.util.Views;

import static com.vaadin.server.FontAwesome.*;

public class RegistrationView extends VerticalLayout implements View {


    public void setUp(){

        //Alles bezieht sich auf den kompletten Browserbildschirm
        this.setSizeFull();

        //Auswahl Profiltyp:
        NativeSelect<String> regType = new NativeSelect<>("Wählen Sie den Profiltyp:");
        regType.setItems("Kunde");

        //Name
        final TextField nameField = new TextField();
        nameField.setCaption(" Name: ");

        //E-Mail
        final TextField emailField = new TextField();
        emailField.setCaption("* E-Mail: ");

        //Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("* Passwort: ");

        //Beides untereinander anordnen
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(regType);
        layout.addComponent(nameField);
        layout.addComponent(emailField);
        layout.addComponent(passwordField);

        //Spacer zwischen Feldern und Button
        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);

        //Kasten zum Login
        Panel panel = new Panel("Bitte Daten zur Registrierung angeben: ");


        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER); //Panel in die Mitte der Seite

        //Login Button mittig hinzufügen
        Button regButton = new Button("Registrierung abschließen", CHECK_CIRCLE);
        layout.addComponent(regButton);
        layout.setComponentAlignment(regButton, Alignment.MIDDLE_CENTER);


        //VerticalLayout mit Login Feldern ins Panel rein
        panel.setContent(layout);
        //Panelgröße passt sich an Inhalt an
        panel.setSizeUndefined();

        regButton.addClickListener(e ->{
            String email = emailField.getValue();
            String password = passwordField.getValue();
            String name = nameField.getValue();
            String rolle = regType.getValue();

            try {
                RegistrationControl.registerUser (email, password, name, rolle);

            } catch (DatabaseException ex ) {
                Notification.show("DB-Fehler:", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                emailField.setValue("");
                passwordField.setValue("");
                nameField.setValue("");
            } catch (EmailException ex) {
                Notification.show("Eingabe-Fehler:", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                emailField.setValue("");
            }

        });

    }

    public void enter(ViewChangeListener.ViewChangeEvent event){
        //User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);

        User user =  ((MyUI) UI.getCurrent()).getUser();

        //Wenn user schon eingeloggt, kommt er direkt auf Main statt auf Login
        if(user != null){
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }else {
            this.setUp();
        }
    }
}
