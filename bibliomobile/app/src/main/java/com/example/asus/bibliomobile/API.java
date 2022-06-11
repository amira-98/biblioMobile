package com.example.asus.bibliomobile;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface API {

    @GET("comptes/{login}/{motdepasse}")
    Call<List<Compte>> getComptes(@Path("login")String login, @Path("motdepasse")String motdepasse);
    @GET("listecategories")
    Call<List<Categorie>> getCategories();
    @GET("auteurs")
    Call<List<Auteur>> getAuteurs();
    @GET("oeuvres/all/{idAut}/{idCat}/{mc}")
    Call<List<Oeuvre>> getOeuvreparautcatmc(@Path("idAut")int idAut,@Path("idCat") int idCat , @Path("mc") String mc);
    @GET("oeuvres")
    Call<List<Oeuvre>> getOeuvres();
    @GET("oeuvre/{idOeuv}")
    Call<Oeuvre> getOeuvre(@Path("idOeuv")int idOeuv);
    @GET("categories/{idCat}/oeuvres")
    Call<List<Oeuvre>> getOeuvresparcat(@Path("idCat") int idCat);
    @GET("auteurs/oeuvres/{idAut}")
    Call<List<Oeuvre>> getOeuvresparaut(@Path("idAut")int idAut);
    @GET("oeuvres/{mc}")
    Call<List<Oeuvre>> getOeuvresparmc(@Path("mc") String mc);
    @GET("oeuvres/{idAut}/{idCat}")
    Call<List<Oeuvre>> getOeuvreparautcat(@Path("idAut")int idAut,@Path("idCat") int idCat );
    @GET("oeuvres/{idAut}/{mc}")
    Call<List<Oeuvre>> getOeuvreparautmc(@Path("idAut")int idAut, @Path("mc") String mc);
    @GET("oeuvres/{idCat}/{mc}")
    Call<List<Oeuvre>> getOeuvreparcatmc(@Path("idCat") int idCat , @Path("mc") String mc);
    @GET("livre/{idOeuv}")
    Call<List<Livre>> getlivreparoeuvre(@Path("idOeuv")int idOeuv);
    @GET("adherent/{idadh}")
    Call<Adherent> getadherent(@Path("idadh")Integer idAdh);
    @GET("emprunt/{idadh}/{idlivre}/{date}")
    Call<Emprunt> addEmprunt(@Path("idadh")Integer idAdh, @Path("idlivre")Integer idlivre, @Path
            ("date")String date);
    @GET("emprunts/{idadh}")
    Call<List<Emprunt>> getempruntsparadherent(@Path("idadh")int idAdh);
}
