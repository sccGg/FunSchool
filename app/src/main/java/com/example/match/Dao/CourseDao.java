package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.match.Entity.Course;

import java.util.List;

@Dao
public interface CourseDao {

    @Insert
    void addClass(Course... courses);

    @Query("SELECT * FROM Course WHERE user_id=:user_id")
    List<Course> getAllClass(int user_id);

    @Query("delete from course WHERE user_id=:user_id")
    void deleteAllClass(int user_id);

    @Query("SELECT * FROM course WHERE startTime=:st and endTime=:et and day=:day")
    Course getAClass(String st, String et, String day);


}
