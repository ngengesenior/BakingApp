package com.apps.ngenge.bakingapp.database.dao;


import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.apps.ngenge.bakingapp.database.entity.RecipeEntity;
import com.apps.ngenge.bakingapp.database.entity.StepEntity;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipeTable")
    List<RecipeEntity> getAllRecipes();

    @Insert
    void insertRecipesps(RecipeEntity... recipeEntities);

    @Query("DELETE FROM recipeTable")
    void deleteAllRecipes();
}
