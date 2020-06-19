package com.example.match.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.match.AppDataBase;
import com.example.match.Dao.CourseDao;
import com.example.match.Entity.Course;
import com.example.match.Entity.User;
import com.example.match.R;
import com.example.match.Tool.DataTool;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTableActivity extends AppCompatActivity {

    private Button addButton,cleanButton;
    private CourseDao courseDao;
    private Activity activity;
    LinearLayout linearLayout;
    public CourseTableActivity() {
        activity=this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_course_table);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        AppDataBase.getInstance(getApplicationContext());
        addButton = findViewById(R.id.addClassButton);
        cleanButton = findViewById(R.id.back_plan);
        courseDao = AppDataBase.instance.courseDao();
        final User user=AppDataBase.instance.userDao().getUserByAccount(DataTool.getUserName(this));
        List<Course> allClass = courseDao.getAllClass(user.getUser_id());
        for (Course aClass : allClass) {
            int day = Integer.parseInt(aClass.getDay());
            switch (day) {
                case 1:
                    linearLayout = findViewById(R.id.Monday);
                    break;
                case 2:
                    linearLayout = findViewById(R.id.Tuesday);
                    break;
                case 3:
                    linearLayout = findViewById(R.id.Wednesday);
                    break;
                case 4:
                    linearLayout = findViewById(R.id.Thursday);
                    break;
                case 5:
                    linearLayout = findViewById(R.id.Friday);
                    break;
            }
            int height = 154;
            View v = LayoutInflater.from(getApplicationContext()).inflate(R.layout.course_card, null);
            v.setY(height * (Integer.parseInt(aClass.getStartTime()) - 1));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                    (ViewGroup.LayoutParams.MATCH_PARENT, (Integer.parseInt(aClass.getEndTime()) - Integer.parseInt(aClass.getStartTime()) + 1) * height - 8);
            v.setLayoutParams(params);
            TextView textView = v.findViewById(R.id.text_view);
            textView.setText(aClass.getClassName() + "\n" + aClass.getClassPlace());
            linearLayout.addView(v);
        }
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity,AddClassActitcity.class);
                startActivity(intent);

            }
        });
        cleanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                courseDao.deleteAllClass(user.getUser_id());
                Toast.makeText(getApplicationContext(),"课程已经清空成功，请重新添加",Toast.LENGTH_LONG).show();
            }
        });
    }
    }

