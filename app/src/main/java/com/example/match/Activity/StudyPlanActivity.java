package com.example.match.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.match.AppDataBase;
import com.example.match.Entity.StudyPlan;
import com.example.match.Entity.User;
import com.example.match.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StudyPlanActivity extends AppCompatActivity {
     private User user;
     private StudyPlan studyPlan;
    TextView one_plan;
    TextView two_plan;
    TextView three_plan;
    TextView four_plan;
    TextView five_plan;
    public StudyPlanActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_study_plan);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        AppDataBase.getInstance(getApplicationContext());
        Button addPlan=findViewById(R.id.addStudyButton);
        addPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),AddStudyPlanActivity.class);
                startActivity(intent);
            }
        });
        one_plan = findViewById(R.id.one_plan);
        two_plan = findViewById(R.id.two_plan);
        three_plan = findViewById(R.id.three_plan);
        four_plan = findViewById(R.id.four_plan);
        five_plan = findViewById(R.id.five_plan);
        InitData();
    }

    //获取数据
    public void InitData() {
        user = AppDataBase.instance.userDao().getUserByAccount(getSharedPreferences("user", MODE_PRIVATE).getString("username", ""));
        studyPlan = AppDataBase.instance.studyPlanDao().getStudyPlan(user.getUser_id());
        if (studyPlan != null) {
            one_plan.setText(studyPlan.getOne_plan());
            two_plan.setText(studyPlan.getTwo_plan());
            three_plan.setText(studyPlan.getThree_plan());
            four_plan.setText(studyPlan.getFour_plan());
            five_plan.setText(studyPlan.getFive_plan());
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
       InitData();
    }
}
