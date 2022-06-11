package com.example.asus.bibliomobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Emprunt {
    @SerializedName("id")
    @Expose
    private Id id;
    @SerializedName("date_retour_effective")
    @Expose
    private Object dateRetourEffective;
    @SerializedName("date_retour_theorique")
    @Expose
    private String dateRetourTheorique;
    @SerializedName("nb_avertissement")
    @Expose
    private Integer nbAvertissement;
    @SerializedName("type_support")
    @Expose
    private Object typeSupport;

    public Id getId() {
        return id;
    }

    public void setId(Id id) {
        this.id = id;
    }

    public Object getDateRetourEffective() {
        return dateRetourEffective;
    }

    public void setDateRetourEffective(Object dateRetourEffective) {
        this.dateRetourEffective = dateRetourEffective;
    }

    public String getDateRetourTheorique() {
        return dateRetourTheorique;
    }

    public void setDateRetourTheorique(String dateRetourTheorique) {
        this.dateRetourTheorique = dateRetourTheorique;
    }

    public Integer getNbAvertissement() {
        return nbAvertissement;
    }

    public void setNbAvertissement(Integer nbAvertissement) {
        this.nbAvertissement = nbAvertissement;
    }

    public Object getTypeSupport() {
        return typeSupport;
    }

    public void setTypeSupport(Object typeSupport) {
        this.typeSupport = typeSupport;
    }
}



