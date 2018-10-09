package com.apps.ngenge.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.apps.ngenge.bakingapp.retro_service.MiriamRecipeService;

import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RecipeListActivity extends AppCompatActivity {

    private Recipe recipe;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recipe_list);
        makeRetroCall();
    }


    private void makeRetroCall()
    {
        OkHttpClient client = new OkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("https://d17h27t6h515a5.cloudfront.net")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MiriamRecipeService service = retrofit.create(MiriamRecipeService.class);

        Call<List<Recipe>> call = service.listOfRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(Call<List<Recipe>> call, Response<List<Recipe>> response) {
                System.out.println("Successful call to service :"+ response.body().size());
            }

            @Override
            public void onFailure(Call<List<Recipe>> call, Throwable t) {

                System.out.println("Failed to call service:"+ t.getMessage());
            }
        });

    }
}
