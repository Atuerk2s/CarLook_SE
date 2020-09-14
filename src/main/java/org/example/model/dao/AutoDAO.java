package org.example.model.dao;

import org.example.model.objects.dto.Auto;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AutoDAO extends AbstractDAO{

    public static AutoDAO dao = null;

    private AutoDAO(){

    }

    public static AutoDAO getInstance(){
        if(dao == null){
            dao = new AutoDAO();
        }
        return dao;
    }


    public List<Auto> getAutoByMarke(String marke){

        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * "

                    + "FROM oemerdb.auto_1 "
                    + "WHERE oemerdb.auto_1.marke = \'" + marke + "\' "
                    //"OR WHERE oemerdb.auto_1.baujahr = \'" + suchbegriff + "\' "
            );

        } catch (SQLException ex){

        }
        if(rs == null) return null;


        List<Auto> liste = new ArrayList<>();

        Auto auto = null;

        try{
            while(rs.next()){
                auto = new Auto();
                auto.setId(rs.getInt(1));
                auto.setMarke(rs.getString(2));
                auto.setBaujahr(rs.getInt(3));
                auto.setBeschreibung(rs.getString(4));

                liste.add(auto);
            }
        }catch (SQLException ex){
            ex.printStackTrace();
        }
        return liste;
    }


}