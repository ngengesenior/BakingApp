package com.apps.ngenge.bakingapp.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;


import com.apps.ngenge.bakingapp.R;
import com.apps.ngenge.bakingapp.adapters.RecipeListAdapter;
import com.apps.ngenge.bakingapp.models.Recipe;
import com.apps.ngenge.bakingapp.retro_service.MiriamApiServiceHelper;
import com.apps.ngenge.bakingapp.retro_service.MiriamRecipeService;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import com.apps.ngenge.bakingapp.utils.Utils;

public class RecipeListActivity extends AppCompatActivity implements
        RecipeListAdapter.RecipeClickListener{
    private RecipeListAdapter listAdapter;
    private  MiriamRecipeService miriamRecipeService;
    @BindView(R.id.recipesList)
    RecyclerView recipesListRecyclerView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_recipe_list);
        ButterKnife.bind(this);
        miriamRecipeService = MiriamApiServiceHelper.getInstance(this);

        makeRetroCall();
    }


    private void makeRetroCall()
    {

        Call<List<Recipe>> call = miriamRecipeService.listOfRecipes();
        call.enqueue(new Callback<List<Recipe>>() {
            @Override
            public void onResponse(@NonNull Call<List<Recipe>> call, @NonNull Response<List<Recipe>> response) {

                listAdapter = new RecipeListAdapter(response.body(),RecipeListActivity.this,RecipeListActivity.this);
                recipesListRecyclerView.setAdapter(listAdapter);
            }

            @Override
            public void onFailure(@NonNull Call<List<Recipe>> call, Throwable t) {

                t.printStackTrace();

            }
        });

    }

    @Override
    public void onRecipeItemClicked(Recipe recipe) {
        Intent intent = new Intent(this,RecipeDetailsActivity.class);
        intent.putExtra(Utils.RECIPE_TAG,recipe);
        startActivity(intent);

    }


}
