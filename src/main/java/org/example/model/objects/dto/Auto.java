package org.example.model.objects.dto;

public class Auto implements java.io.Serializable{

    private String marke;
    private Integer id;
    private Integer baujahr;
    private String description;

    //Zugeordneter Vertriebler, der das Auto eingestellt hat
    private User vertriebler;

    public Auto(){

    }

    public Auto(String name, Integer id, Integer ort, String description){
        this.marke = name;
        this.id = id;
        this.baujahr = ort;
        this.description = description;
    }

    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id = id;
    }
    public String getMarke(){
        return marke;
    }
    public void setMarke(String marke){
        this.marke = marke;
    }
    public Integer getBaujahr(){
        return baujahr;
    }
    public void setBaujahr(Integer baujahr){
        this.baujahr = baujahr;
    }
    public String getBeschreibung(){
        return description;
    }
    public void setBeschreibung(String description){
        this.description = description;
    }

    public String toString(){
        return getMarke();
    }
}
