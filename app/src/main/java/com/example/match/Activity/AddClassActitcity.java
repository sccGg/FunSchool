package com.example.match.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.match.R;

public class AddClassActitcity extends AppCompatActivity {


    public AddClassActitcity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_add_class);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}
