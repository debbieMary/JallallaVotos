package com.jallalla.jallallavotos.Network;


public class ApiUtils {

    private ApiUtils(){
    };


    public static final String API_URL = "https://api-encuestas-bo.herokuapp.com/";
    //public static final String API_URL = "https://nodejs-notifications.firebaseio.com/";

    public static ApiService getJallallaService(){
        return RetrofitClient.getClient(API_URL).create(ApiService.class);
    }

}
