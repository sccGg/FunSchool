package com.example.match.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.match.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTableFragment extends Fragment {


    public CourseTableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_course_table,container,false);


        return view;
    }
}
