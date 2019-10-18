package com.example.anew.Retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static ServiceRetrofit instance;

    public static ServiceRetrofit getInstance() {

        if (instance == null) {


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://crm.phanmemninja.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            instance = retrofit.create(ServiceRetrofit.class);
        }

        return instance;

    }
}
