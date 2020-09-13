package org.example.model.objects.entities;

import org.example.model.objects.dto.Role;

public class Registrierung {
    private String name;
    private String email;
    private String passwort;
    private Role role;


    public String getName() {
        return name;
    }

    public void setName(String name) { this.name = name; }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) { this.email = email; }

    public String getPasswort() {
        return passwort;
    }

    public void setPasswort(String passwort) {
        this.passwort = passwort;
    }

    public Role getRole() { return role; }

    public void setRole(Role rolle) { this.role = rolle; }

}
