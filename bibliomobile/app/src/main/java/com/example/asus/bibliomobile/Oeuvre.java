package com.example.asus.bibliomobile;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Oeuvre {

    @SerializedName("id_oeuvre")
    @Expose
    private Integer idOeuvre;
    @SerializedName("titre")
    @Expose
    private String titre;
    @SerializedName("nb_dvd")
    @Expose
    private Integer nbDvd;
    @SerializedName("nb_sup_papier")
    @Expose
    private Integer nbSupPapier;
    @SerializedName("categorie")
    @Expose
    private Categorie categorie;
    @SerializedName("auteur")
    @Expose
    private Auteur auteur;
    @SerializedName("nbLivreDispo")
    @Expose
    private Integer nbLivreDispo;

    public Integer getIdOeuvre() {
        return idOeuvre;
    }

    public void setIdOeuvre(Integer idOeuvre) {
        this.idOeuvre = idOeuvre;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Integer getNbDvd() {
        return nbDvd;
    }

    public void setNbDvd(Integer nbDvd) {
        this.nbDvd = nbDvd;
    }

    public Integer getNbSupPapier() {
        return nbSupPapier;
    }

    public void setNbSupPapier(Integer nbSupPapier) {
        this.nbSupPapier = nbSupPapier;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur auteur) {
        this.auteur = auteur;
    }

    public Integer getNbLivreDispo() {
        return nbLivreDispo;
    }

    public void setNbLivreDispo(Integer nbLivreDispo) {
        this.nbLivreDispo = nbLivreDispo;
    }
}
