package com.apps.ngenge.bakingapp.database.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "stepsTable",foreignKeys = @ForeignKey(entity = RecipeEntity.class,parentColumns = "id",
childColumns = "recipeId"),indices = @Index(value = "recipeId"))
public class StepEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "shortDescription" )
    private String shortDescription;

    @ColumnInfo(name = "description" )
    private String description;

    @ColumnInfo(name = "videoUR")
    private String videoURL;
    @ColumnInfo(name = "thumbnailURL")
    private String thumbnailURL;

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

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVideoURL() {
        return videoURL;
    }

    public void setVideoURL(String videoURL) {
        this.videoURL = videoURL;
    }

    public String getThumbnailURL() {
        return thumbnailURL;
    }

    public void setThumbnailURL(String thumbnailURL) {
        this.thumbnailURL = thumbnailURL;
    }
}
