package com.example.match.Activity;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.match.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTableActivity extends AppCompatActivity {


    public CourseTableActivity() {
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_course_table);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
    }
}
