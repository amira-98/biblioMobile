package com.example.asus.bibliomobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Livre {
    @SerializedName("id_livre")
    @Expose
    private Integer idLivre;
    @SerializedName("date_edition")
    @Expose
    private String dateEdition;
    @SerializedName("etat")
    @Expose
    private Integer etat;
    @SerializedName("qte")
    @Expose
    private Integer qte;
    @SerializedName("oeuvre")
    @Expose
    private Oeuvre oeuvre;

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public String getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(String dateEdition) {
        this.dateEdition = dateEdition;
    }

    public Integer getEtat() {
        return etat;
    }

    public void setEtat(Integer etat) {
        this.etat = etat;
    }

    public Integer getQte() {
        return qte;
    }

    public void setQte(Integer qte) {
        this.qte = qte;
    }

    public Oeuvre getOeuvre() {
        return oeuvre;
    }

    public void setOeuvre(Oeuvre oeuvre) {
        this.oeuvre = oeuvre;
    }

}
