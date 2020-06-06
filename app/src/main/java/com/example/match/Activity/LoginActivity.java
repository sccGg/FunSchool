package com.example.match.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.match.AppDataBase;
import com.example.match.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        AppDataBase.getInstance(getApplicationContext());
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}
