package com.apps.ngenge.bakingapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;


import com.apps.ngenge.bakingapp.database.entity.IngredientEntity;

import java.util.List;

@Dao
public interface IngredientDao {

    @Query("SELECT * FROM ingredientsTable")
    List<IngredientEntity> getAllIngredients();

    @Insert
    void insertIngredients(IngredientEntity ... ingredientEntities);


    @Query("DELETE FROM ingredientsTable")
    void deleteIngredients();

}
