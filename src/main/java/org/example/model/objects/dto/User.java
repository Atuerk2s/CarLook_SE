package org.example.model.objects.dto;

import org.example.model.dao.RoleDAO;

public class User {

    private String name = null;
    private String email = null;
    private Role role = null;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email = email;
    }

    public boolean hasRole(String rolle) {
        //Lazy Load
        if(this.role == null){
            getRole();
        }
        if(role.getBezeichnung().equals(rolle)) {return true;}

        return false;
    }

    public String getRole(){
        return role.getBezeichnung();
    }

    public void setRole(String role) { this.role.setBezeichnung(role); }
}
