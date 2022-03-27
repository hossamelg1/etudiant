package com.example.etudiants.remote;

import com.example.etudiants.model.Etudiant;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface EtudiantService {

    @POST("addEtudiant/")
    Call<Etudiant> addUser(@Body Etudiant user);

    @GET("Etudiants")
    Call<List<Etudiant>> getEtudiants();
}
