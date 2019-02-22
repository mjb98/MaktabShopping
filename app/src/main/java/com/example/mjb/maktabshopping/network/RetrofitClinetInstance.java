package com.example.mjb.maktabshopping.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClinetInstance {
    private static Retrofit retrofitInstance;
    public static final String BASE_URL = "https://woocommerce.maktabsharif.ir/wp-json/wc/v3/";

    public static Retrofit getRetrofitClinetInstance() {
        if(retrofitInstance == null) {
            retrofitInstance = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofitInstance;
    }

}
