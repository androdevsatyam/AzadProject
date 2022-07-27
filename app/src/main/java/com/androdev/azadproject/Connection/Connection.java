package com.androdev.azadproject.Connection;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connection {

    public static ConnectionInterface getCon(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("52.5.173.237/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(ConnectionInterface.class);
    }
}
