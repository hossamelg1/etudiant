package com.example.etudiants.remote;

import com.example.etudiants.model.Etudiant;

public class ApiUtils {
    private ApiUtils(){
    };

    //public static final String API_URL = "http://192.168.1.106:9090";
    public static final String API_URL = "http://192.168.1.102:9090";

    public static EtudiantService getUserService(){
        return RetrofitClient.getClient(API_URL).create(EtudiantService.class);
    }
}
