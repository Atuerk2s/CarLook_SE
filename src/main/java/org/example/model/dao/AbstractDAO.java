package org.example.model.dao;

import org.example.process.control.exceptions.DatabaseException;
import org.example.services.db.JDBCConnection;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class AbstractDAO { //<E>

    //CRUD PATTERN
    //public void create(E e){
        //SQL so wie Typ gegeben
    //}

    protected Statement getStatement(){
        Statement statement = null;

        try{
            statement = JDBCConnection.getInstance().getStatement();
        } catch(DatabaseException ex){
            ex.printStackTrace();
        }
        return statement;
    }

    protected PreparedStatement getPreparedStatement(String sql){
        PreparedStatement statement = null;
        try{
            statement = JDBCConnection.getInstance().getPreparedStatement(sql);
        } catch(DatabaseException ex){
            ex.printStackTrace();
        }
        return statement;

    }
}
