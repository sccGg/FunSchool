package com.example.match;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

import com.example.match.Entity.Article;
import com.example.match.Entity.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AppDataBase.getInstance(getApplicationContext());
        setContentView(R.layout.activity_main);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}
