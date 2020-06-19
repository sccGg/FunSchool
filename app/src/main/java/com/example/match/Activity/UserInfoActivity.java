package com.example.match.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.match.AppDataBase;
import com.example.match.Entity.User;
import com.example.match.Entity.UserInfo;
import com.example.match.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UserInfoActivity extends AppCompatActivity {
    private Button button;
    TextView username;
    TextView sex;
    TextView age;
    TextView time;
    TextView addr;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_info);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        username=findViewById(R.id.content1);
        sex=findViewById(R.id.content2);
        age=findViewById(R.id.content3);
        time=findViewById(R.id.content4);
        addr=findViewById(R.id.content5);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),UserInfoEditActivity.class);
                startActivity(intent);
            }
        });
        load();
    }

    public void load(){
        User user = AppDataBase.instance.userDao().getUserByAccount(getSharedPreferences("user",MODE_PRIVATE).getString("username",""));
        UserInfo userinfo =AppDataBase.instance.userInfoDao().getUserInfo(user.getUser_id());
        username.setText(userinfo.getUserName());
        sex.setText(userinfo.getSex());
        age.setText(userinfo.getAge());
        time.setText(userinfo.getBirthday());
        addr.setText(userinfo.getAdd());
    }
}
