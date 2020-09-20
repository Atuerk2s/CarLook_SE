package org.example.process.control;

import com.vaadin.ui.UI;
import org.example.process.control.exceptions.DatabaseException;
import org.example.process.control.exceptions.EmailException;
import org.example.services.db.JDBCConnection;
import org.example.services.util.Views;


import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegistrationControl {

    private static RegistrationControl process = null;

    private RegistrationControl(){
    }

    public static RegistrationControl getInstance(){
        if(process == null){
            process = new RegistrationControl();
        }
        return process;
    }


    public static void registerUser(String email, String passwort, String name, String rolle) throws DatabaseException, EmailException {

        String sql = "insert into oemerdb.user (login, password, vorname, rolle) values (?,?,?,?)";

        PreparedStatement statement = JDBCConnection.getInstance().getPreparedStatement(sql);


        //Angaben in user-Tabelle schreiben:
        try {

            //Fehlerfall Reg.: keine Email vorhanden
            if (rolle.equals("Kunde")) {
                if(email.equals("")) {
                    throw new EmailException("Keine E-Mail eingegeben!");
                }
            }

            //Relevant, wenn Vertriebler-Sicht implementiert wird
            if (rolle.equals("Vertriebler")) {
                if(!email.equals(name + "@carlook.de")) {
                    throw new EmailException("Keine gültige Firmen-E-Mail eingegeben!");
                }
            }


            statement.setString(1, email);
            statement.setString(2, passwort);
            statement.setString(3, name);
            statement.setString(4, rolle);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DatabaseException("Error: DB-Fehler!");

        } finally {
            JDBCConnection.getInstance().closeConnection();
        }

        //Zurück zur Login-Seite navigieren:
        UI.getCurrent().getNavigator().navigateTo(Views.LOGIN);

    }

}


