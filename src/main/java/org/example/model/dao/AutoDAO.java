package org.example.model.dao;

import org.example.model.objects.dto.Auto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
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

    //    Statement statement = this.getStatement();
        PreparedStatement statement = null;
        ResultSet rs = null;
        marke = marke.toUpperCase();
        try{
            String sql = "SELECT * "

                    + "FROM oemerdb.auto "
                    // + "WHERE oemerdb.auto.marke = \'" + marke + "\' "
                    + "WHERE UPPER(marke) LIKE '%' || ? || '%' ";

            statement = this.getPreparedStatement(sql);
            statement.setString(1,marke);

            rs = statement.executeQuery();

        } catch (SQLException ex){

        }
        if(rs == null) return Collections.emptyList();


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
    public Auto getAutoByAutoID(int autoid) throws SQLException {
        String sql = "SELECT * FROM oemerdb.auto " +
                " WHERE id = ?";
        PreparedStatement statement = this.getPreparedStatement(sql);
        statement.setInt(1, autoid);
        Auto auto = new Auto();
        try (ResultSet set = statement.executeQuery()) {
            if (set.next()) {
                auto.setId(set.getInt(1));
                auto.setMarke(set.getString(2));
                auto.setBaujahr(set.getInt(3));
                auto.setBeschreibung(set.getString(4));
            }
        }
        return auto;

    }

    public List<Integer> getAutoIDbyResID(String resid) throws SQLException {
        String sql = "SELECT * FROM oemerdb.reservierung " +
                " WHERE userid = ?";
        PreparedStatement statement = this.getPreparedStatement(sql);
        statement.setString(1, resid);
        List<Integer> autoIDList = new ArrayList<Integer>();

        try (ResultSet set = statement.executeQuery()) {
            while (set.next()) {
                autoIDList.add(set.getInt(7));
            }
        }
        return autoIDList;
    }

}