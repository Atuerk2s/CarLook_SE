package org.example.gui.views;

import com.vaadin.navigator.View;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.shared.ui.ContentMode;
import com.vaadin.ui.*;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.User;
import org.example.process.control.LoginControl;
import org.example.process.control.exceptions.DatabaseException;
import org.example.process.control.exceptions.NoSuchUserOrPassword;
import org.example.services.util.Roles;
import org.example.services.util.Views;
import java.util.logging.*;
import java.sql.*;
import com.vaadin.server.*;

public class LoginView extends VerticalLayout implements View {

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event){
        User user = (User) VaadinSession.getCurrent().getAttribute(Roles.CURRENT_USER);


        //Wenn user schon eingeloggt, kommt er direkt auf Main statt auf Login
        if(user != null){
            UI.getCurrent().getNavigator().navigateTo(Views.MAIN);
        }else {
            this.setUp();
        }
    }

    public void setUp(){

        //Alles bezieht sich auf den kompletten Browserbildschirm
        this.setSizeFull();

        //User ID
        final TextField emailField = new TextField();
        emailField.setCaption("E-Mail: ");

        //Passwort
        final PasswordField passwordField = new PasswordField();
        passwordField.setCaption("Passwort: ");

        //Beides untereinander anordnen
        VerticalLayout layout = new VerticalLayout();
        layout.addComponent(emailField);
        layout.addComponent(passwordField);

        //Spacer zwischen Feldern und Button
        Label label = new Label("&nbsp;", ContentMode.HTML);
        layout.addComponent(label);

        //Kasten um Login
        Panel panel = new Panel("Bitte Login-Daten angeben: ");
        //panel.addStyleName("login"); Würde SCSS in Community funktionieren...

        this.addComponent(panel);
        this.setComponentAlignment(panel, Alignment.MIDDLE_CENTER); //Panel in die Mitte der Seite

        //Login Button mittig hinzufügen
        Button loginButton = new Button("Login", FontAwesome.HOME);
        layout.addComponent(loginButton);
        layout.setComponentAlignment(loginButton, Alignment.MIDDLE_CENTER);

        //Registrieren-Button hinzufügen:
        Button registerButton = new Button("Registrieren", FontAwesome.USER);
        layout.addComponent(registerButton);
        layout.setComponentAlignment(registerButton, Alignment.MIDDLE_CENTER);

        registerButton.addClickListener(e -> UI.getCurrent().getNavigator().navigateTo(Views.REG));

        //VerticalLayout mit Login Feldern ins Panel rein
        panel.setContent(layout);
        //Panelgröße passt sich an Inhalt an
        panel.setSizeUndefined();

        loginButton.addClickListener(e ->{
            String email = emailField.getValue();
            String password = passwordField.getValue();

            try {
                LoginControl.checkAuthentication(email, password);

            } catch (NoSuchUserOrPassword noSuchUserOrPassword) {
                Notification.show("Benutzerfehler", "E-Mail oder Passwort falsch!", Notification.Type.ERROR_MESSAGE);
                emailField.setValue("");
                passwordField.setValue("");

            } catch (DatabaseException ex) {
                Notification.show("DB-Fehler", ex.getReason(), Notification.Type.ERROR_MESSAGE);
                emailField.setValue("");
                passwordField.setValue("");
            }
        });
}}
