package com.example.match.Activity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.match.AppDataBase;
import com.example.match.Dao.CourseDao;
import com.example.match.Entity.Course;
import com.example.match.Entity.User;
import com.example.match.R;
import com.example.match.Tool.DataTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddClassActitcity extends AppCompatActivity {
    private EditText className,classPlace,classStart,classEnd,weekDay;
    private Button backButton,sureButton;
    private CourseDao courseDao;
   private User user;
    public AddClassActitcity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_class);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        className = findViewById(R.id.add_class1);
        classPlace = findViewById(R.id.add_class2);
        classStart = findViewById(R.id.add_class3);
        classEnd = findViewById(R.id.add_class4);
        weekDay = findViewById(R.id.add_class5);

        backButton = findViewById(R.id.button4);
        sureButton = findViewById(R.id.SureButton);

        courseDao = AppDataBase.instance.courseDao();
        user = AppDataBase.instance.userDao().getUserByAccount(getSharedPreferences("user", Context.MODE_PRIVATE).getString("username",""));
        sureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (check()){
                    courseDao = AppDataBase.instance.courseDao();
                    Course course = new Course();
                    course.setClassName(className.getText().toString());
                    course.setClassPlace(classPlace.getText().toString());
                    course.setStartTime(classStart.getText().toString());
                    course.setEndTime(classEnd.getText().toString());
                    course.setDay(weekDay.getText().toString());
                    course.setUser_id(user.getUser_id());
                    courseDao.addClass(course);
                    Toast.makeText(getApplicationContext(), "课程添加成功", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public boolean check(){
        if (CheckChar(className.getText().toString())){
            Toast.makeText(getApplicationContext(),"教室名包含非法字符",Toast.LENGTH_LONG).show();
            return false;
        }

        if (className.getText().length()==0){
            Toast.makeText(getApplicationContext(),"课程名不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if (classPlace.getText().length()==0){
            Toast.makeText(getApplicationContext(),"教室名不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if (classStart.getText().length()==0){
            Toast.makeText(getApplicationContext(),"上课时间不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if (classEnd.getText().length()==0){
            Toast.makeText(getApplicationContext(),"下课时间不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if (weekDay.getText().length()==0){
            Toast.makeText(getApplicationContext(),"周几不能为空",Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkDay(Integer.parseInt(weekDay.getText().toString()))){
            Toast.makeText(getApplicationContext(),"周几输入有错",Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkTime(Integer.parseInt(classStart.getText().toString()))&&checkTime(Integer.parseInt(classEnd.getText().toString()))){
            Toast.makeText(getApplicationContext(),"上课时间或下课时间输入有错",Toast.LENGTH_LONG).show();
            return false;
        }

        if (Integer.parseInt(classStart.getText().toString())>Integer.parseInt(classEnd.getText().toString())){
            Toast.makeText(getApplicationContext(),"上课时间或下课时间输入有误",Toast.LENGTH_LONG).show();
            return false;
        }

        if (checkPlace(classPlace.getText().toString())){
            Toast.makeText(getApplicationContext(),"教室输入有误",Toast.LENGTH_LONG).show();
            return false;
        }

        if (courseDao.getAClass(classStart.getText().toString(),classEnd.getText().toString(),weekDay.getText().toString())!=null){
            Toast.makeText(getApplicationContext(),"课程冲突",Toast.LENGTH_LONG).show();
            return false;
        }
        return true;
    }

    public boolean CheckChar(String text) {
        String regEx = "[ _`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]|\n|\r|\t";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(text);
        return m.find();
    }

    public boolean checkDay(int day){
        if (day<0||day>5){
            return true;
        }
        return false;
    }

    public boolean checkTime(int time){
        if(time <0||time>10){
            return true;
        }
        return false;
    }

    public boolean checkPlace(String place){
        if (place.contains("GS:")||place.contains("GJ:")){
            return false;
        }
        return true;
    }
}
