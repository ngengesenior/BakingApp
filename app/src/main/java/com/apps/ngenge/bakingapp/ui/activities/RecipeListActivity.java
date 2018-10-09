package com.apps.ngenge.bakingapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.apps.ngenge.bakingapp.retro_service.MiriamRecipeService;

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

        Call<Recipe> call = service.listOfRecipes();
        call.enqueue(new Callback<Recipe>() {
            @Override
            public void onResponse(Call<Recipe> call, Response<Recipe> response) {
                Recipe recipe = response.body();
                Log.d("RetroRecipe",recipe.toString());
            }

            @Override
            public void onFailure(Call<Recipe> call, Throwable t) {

                Log.e("RetroErrot",t.getMessage());
            }
        });
    }
}
