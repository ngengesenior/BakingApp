package com.apps.ngenge.bakingapp.database.entity;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ingredientsTable",foreignKeys = @ForeignKey(entity = RecipeEntity.class,
parentColumns = "id",childColumns = "recipeId" ),indices = @Index(value = "recipeId"))
public class IngredientEntity {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "quantity")
    private double quantity;

    @ColumnInfo(name = "measure")
    private String measure;

    @ColumnInfo(name = "ingredient")
    private String ingredient;

    @ColumnInfo(name = "recipeId")
    private int recipeId;


    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }
}
