package com.example.asus.bibliomobile;

import com.example.asus.bibliomobile.API;

public class APIUTILS {
    private APIUTILS (){}

        public static final String  BASE_URL="http://10.0.2.2:8080/Serviceweb/bibliotheque/";
   public static API getAPI()
        {return RetrofitClient.getClient(BASE_URL).create(API.class);}

    }

