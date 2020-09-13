package org.example.model.dao;

import org.example.model.objects.dto.Role;
import org.example.model.objects.dto.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RoleDAO extends AbstractDAO{

    public static RoleDAO dao = null;

    private RoleDAO(){

    }
    //Als Singleton realisiert
    public static RoleDAO getInstance(){
        if(dao == null){
            dao = new RoleDAO();
        }
        return dao;
    }

    public List<Role> getRolesForUser(User user){
        Statement statement = this.getStatement();

        ResultSet rs = null;

        try{
            rs = statement.executeQuery("SELECT * "
            + "FROM oemerdb.user_to_rolle "
            + "WHERE oemerdb.user_to_rolle.login = \'" + user.getEmail() + "\' ");
        } catch(SQLException ex){
            ex.printStackTrace();
        }

        if(rs == null) return null;

        List<Role> liste = new ArrayList<Role>();
        Role role = null;

        try{
            while(rs.next()){
                role = new Role();
                role.setBezeichnung(rs.getString(2));
                liste.add(role);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return liste;


    }
}
