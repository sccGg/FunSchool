package com.example.match.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.match.Entity.StudyPlan;

@Dao
public interface StudyPlanDao {
    @Query("SELECT * FROM studyplan WHERE user_id=:user_id")
    StudyPlan getStudyPlan(int user_id);

    @Insert
    void addStudyPlan(StudyPlan studyPlan);
}
