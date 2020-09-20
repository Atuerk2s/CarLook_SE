package org.example.process.control;

import com.vaadin.ui.UI;
import org.example.gui.ui.MyUI;
import org.example.model.objects.dto.User;
import org.example.process.control.exceptions.DatabaseException;
import org.example.process.control.exceptions.NoSuchUserOrPassword;
import org.example.services.db.JDBCConnection;
import org.example.services.util.Views;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginControl {

    public static void checkAuthentication(String email, String passwort) throws NoSuchUserOrPassword, DatabaseException {

        //DB Zugriff, login ist primary key
        //Objekt-Relationen Mapping
        ResultSet set;


        try {
            Statement statement = JDBCConnection.getInstance().getStatement();

            set = statement.executeQuery("SELECT * "
                    + "FROM oemerdb.user "
                    + "WHERE oemerdb.user.login = \'" + email + "\'"
                    + "AND oemerdb.user.password =\'" + passwort + "\'");


        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error: Fehler im SQL Befehl! Bitte den Programmierer informieren!");
        }


        User user = null;


        try {
            if(set.next()){

                user = new User();
                user.setEmail(set.getString(1));
                user.setName(set.getString(3));
                user.setId (set.getInt(1));

            }else{
                //Fehlerfall
                throw new NoSuchUserOrPassword();

            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            JDBCConnection.getInstance().closeConnection();
        }


        //User in Session absetzen und zur Main View navigieren
        //VaadinSession session = UI.getCurrent().getSession();
        //session.setAttribute(Roles.CURRENT_USER, user);

        ((MyUI) UI.getCurrent()).setUser(user);

        UI.getCurrent().getNavigator().navigateTo(Views.MAIN);

    }

    public static void logoutUser() {
        UI.getCurrent().close();
        UI.getCurrent().getPage().setLocation("/CarLook");
    }
}
