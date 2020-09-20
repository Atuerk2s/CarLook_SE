package org.example.model.objects.dto;

public class User {

    private String name = null;
    private String email = null;
    private Role role = null;
    private int id;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getId(){
        return id;
    }

    public void setId(int name){
        this.id = id;
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
