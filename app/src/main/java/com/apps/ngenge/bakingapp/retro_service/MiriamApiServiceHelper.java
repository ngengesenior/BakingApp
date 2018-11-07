package com.apps.ngenge.bakingapp.retro_service;

import android.content.Context;

import com.apps.ngenge.bakingapp.R;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MiriamApiServiceHelper {

    private static MiriamRecipeService miriamApiService;

    public static synchronized MiriamRecipeService getInstance(Context context) {
        if(miriamApiService== null) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(context.getString(R.string.base_url))
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            miriamApiService = retrofit.create(MiriamRecipeService.class);
        }

        return miriamApiService;
    }
}
