package com.apps.ngenge.bakingapp.database.preferredRecipe;

import com.apps.ngenge.bakingapp.database.entity.IngredientEntity;
import com.apps.ngenge.bakingapp.database.entity.RecipeEntity;
import com.apps.ngenge.bakingapp.database.entity.StepEntity;
import com.apps.ngenge.bakingapp.models.Ingredient;
import com.apps.ngenge.bakingapp.models.Step;

import java.util.List;

public class DesiredRecipe {

    private List<IngredientEntity> ingredients;
    private List<StepEntity> steps;
    private RecipeEntity recipe;


    public List<IngredientEntity> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<IngredientEntity> ingredients) {
        this.ingredients = ingredients;
    }

    public List<StepEntity> getSteps() {
        return steps;
    }

    public void setSteps(List<StepEntity> steps) {
        this.steps = steps;
    }

    public RecipeEntity getRecipe() {
        return recipe;
    }

    public void setRecipe(RecipeEntity recipe) {
        this.recipe = recipe;
    }
}
