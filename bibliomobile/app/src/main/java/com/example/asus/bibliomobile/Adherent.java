package com.example.asus.bibliomobile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Adherent {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nom")
    @Expose
    private String nom;
    @SerializedName("prenom")
    @Expose
    private String prenom;
    @SerializedName("adresse")
    @Expose
    private String adresse;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("tel")
    @Expose
    private Integer tel;
    @SerializedName("nb_emprunt_encours")
    @Expose
    private Integer nbEmpruntEncours;
    @SerializedName("blacklist")
    @Expose
    private Boolean blacklist;
    @SerializedName("etu")
    @Expose
    private Boolean etu;
    @SerializedName("filiere")
    @Expose
    private String filiere;
    @SerializedName("annee_inscrip")
    @Expose
    private Integer anneeInscrip;
    @SerializedName("grade")
    @Expose
    private Object grade;
    @SerializedName("departement")
    @Expose
    private String departement;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getTel() {
        return tel;
    }

    public void setTel(Integer tel) {
        this.tel = tel;
    }

    public Integer getNbEmpruntEncours() {
        return nbEmpruntEncours;
    }

    public void setNbEmpruntEncours(Integer nbEmpruntEncours) {
        this.nbEmpruntEncours = nbEmpruntEncours;
    }

    public Boolean getBlacklist() {
        return blacklist;
    }

    public void setBlacklist(Boolean blacklist) {
        this.blacklist = blacklist;
    }

    public Boolean getEtu() {
        return etu;
    }

    public void setEtu(Boolean etu) {
        this.etu = etu;
    }

    public String getFiliere() {
        return filiere;
    }

    public void setFiliere(String filiere) {
        this.filiere = filiere;
    }

    public Integer getAnneeInscrip() {
        return anneeInscrip;
    }

    public void setAnneeInscrip(Integer anneeInscrip) {
        this.anneeInscrip = anneeInscrip;
    }

    public Object getGrade() {
        return grade;
    }

    public void setGrade(Object grade) {
        this.grade = grade;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }


}
