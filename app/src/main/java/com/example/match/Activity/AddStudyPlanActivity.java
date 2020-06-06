package com.example.match.Activity;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.match.AppDataBase;
import com.example.match.Entity.StudyPlan;
import com.example.match.Entity.User;
import com.example.match.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AddStudyPlanActivity extends AppCompatActivity {
    private EditText input_plan;
    private EditText input_day;
    private User user;
    private AlertDialog alertDialog;
    private Button choose_day;
    public AddStudyPlanActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_study_plan);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        input_plan=findViewById(R.id.input_plan);
        choose_day=findViewById(R.id.choose_day);
        user = AppDataBase.instance.userDao().getUserByAccount(getSharedPreferences("user",MODE_PRIVATE).getString("username",""));
         RegisterListener();
    }

    public void RegisterListener(){
        Button summbit_plan = findViewById(R.id.summbit_plan);
        Button back_plan = findViewById(R.id.back_plan);
        summbit_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input_plan.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"请输入学习计划",Toast.LENGTH_LONG).show();
                    return;
                }
                if(choose_day.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"请输入星期几",Toast.LENGTH_LONG).show();
                    return;
                }
                StudyPlan studyPlan=AppDataBase.instance.studyPlanDao().getStudyPlan(user.getUser_id());
                if(studyPlan==null){
                    studyPlan=new StudyPlan();
                    studyPlan.setUser_id(user.getUser_id());
                    studyPlan.setOne_plan(" ");
                    studyPlan.setTwo_plan(" ");
                    studyPlan.setThree_plan(" ");
                    studyPlan.setFour_plan(" ");
                    studyPlan.setFive_plan(" ");
                }
                switch (choose_day.getText().toString()){
                    case "周一":
                        studyPlan.setOne_plan(input_plan.getText().toString());
                        break;
                    case "周二":
                        studyPlan.setTwo_plan(input_plan.getText().toString());
                        break;
                    case "周三":
                        studyPlan.setThree_plan(input_plan.getText().toString());
                        break;
                    case "周四":
                        studyPlan.setFour_plan(input_plan.getText().toString());
                        break;
                    case "周五":
                        studyPlan.setFive_plan(input_plan.getText().toString());
                        break;
                }
                AppDataBase.instance.studyPlanDao().addStudyPlan(studyPlan);
                Toast.makeText(getApplicationContext(),"添加成功",Toast.LENGTH_SHORT).show();
            }
        });
        choose_day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showList();
            }
        });
        back_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    public void showList(){
        final String[] items = {"周一", "周二", "周三", "周四","周五"};
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
        alertBuilder.setTitle("请选择时间");
        alertBuilder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                choose_day.setText(items[i]);
                alertDialog.dismiss();
            }
        });
        alertDialog = alertBuilder.create();
        alertDialog.show();
    }


}
