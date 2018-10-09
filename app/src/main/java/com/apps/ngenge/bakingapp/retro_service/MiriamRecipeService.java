package com.apps.ngenge.bakingapp.retro_service;

import com.apps.ngenge.bakingapp.models.Recipe;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MiriamRecipeService {

    @GET("/topher/2017/May/59121517_baking/baking.json")
    public Call<Recipe> listOfRecipes();
}
