package com.apps.ngenge.bakingapp.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.apps.ngenge.bakingapp.database.entity.StepEntity;

import java.util.List;

@Dao
public interface StepDao {

    @Query("SELECT * FROM stepsTable")
    List<StepEntity> getAllSteps();

    @Insert
    void insertSteps(StepEntity ... stepEntities);

    @Query("DELETE FROM stepsTable")
    void deleteSteps();


}
