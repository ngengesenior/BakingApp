package com.apps.ngenge.bakingapp.database;

import android.arch.persistence.room.Database;

import com.apps.ngenge.bakingapp.database.dao.IngredientDao;
import com.apps.ngenge.bakingapp.database.dao.RecipeDao;
import com.apps.ngenge.bakingapp.database.dao.StepDao;
import com.apps.ngenge.bakingapp.database.entity.IngredientEntity;
import com.apps.ngenge.bakingapp.database.entity.RecipeEntity;
import com.apps.ngenge.bakingapp.database.entity.StepEntity;

@Database(entities = {StepEntity.class,IngredientEntity.class,RecipeEntity.class},version = 1)
public abstract class RecipeDatabase {
    public abstract StepDao stepDao();
    public abstract IngredientDao ingredientDao();
    public abstract RecipeDao recipeDao();

}
