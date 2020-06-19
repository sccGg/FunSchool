package com.example.match.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class StudyPlan {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int plan_id;
    @ColumnInfo
    private int user_id;
    @ColumnInfo
    private String one_plan;
    @ColumnInfo
    private String two_plan;
    @ColumnInfo
    private String three_plan;
    @ColumnInfo
    private String four_plan;
    @ColumnInfo
    private String five_plan;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getOne_plan() {
        return one_plan;
    }

    public void setOne_plan(String one_plan) {
        this.one_plan = one_plan;
    }

    public String getTwo_plan() {
        return two_plan;
    }

    public void setTwo_plan(String two_plan) {
        this.two_plan = two_plan;
    }

    public String getThree_plan() {
        return three_plan;
    }

    public void setThree_plan(String three_plan) {
        this.three_plan = three_plan;
    }

    public String getFour_plan() {
        return four_plan;
    }

    public void setFour_plan(String four_plan) {
        this.four_plan = four_plan;
    }

    public String getFive_plan() {
        return five_plan;
    }

    public void setFive_plan(String five_plan) {
        this.five_plan = five_plan;
    }
}
