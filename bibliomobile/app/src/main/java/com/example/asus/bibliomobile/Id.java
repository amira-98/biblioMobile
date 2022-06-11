package com.example.asus.bibliomobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Id {
    @SerializedName("id_adherent")
    @Expose
    private Integer idAdherent;
    @SerializedName("id_livre")
    @Expose
    private Integer idLivre;
    @SerializedName("date_emprunt")
    @Expose
    private Integer dateEmprunt;

    public Integer getIdAdherent() {
        return idAdherent;
    }

    public void setIdAdherent(Integer idAdherent) {
        this.idAdherent = idAdherent;
    }

    public Integer getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(Integer idLivre) {
        this.idLivre = idLivre;
    }

    public Integer getDateEmprunt() {
        return dateEmprunt;
    }

    public void setDateEmprunt(Integer dateEmprunt) {
        this.dateEmprunt = dateEmprunt;
    }

}
