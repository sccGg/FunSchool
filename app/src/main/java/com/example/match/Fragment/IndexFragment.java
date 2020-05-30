package com.example.match.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.match.Activity.CourseTableActivity;
import com.example.match.Activity.StudyPlanActivity;
import com.example.match.Adapter.RecommandListAdapter;
import com.example.match.AppDataBase;
import com.example.match.Entity.Article;
import com.example.match.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {
    private RecyclerView recommand_list;
    private ArrayList<Article> arrayList;
    private View view;
    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_index, container, false);
        recommand_list= view.findViewById(R.id.recommand_list);
        GridLayoutManager manager = new GridLayoutManager(getActivity().getApplicationContext(),2);
        manager.setOrientation(GridLayoutManager.VERTICAL);
        recommand_list.setLayoutManager(manager);
        recommand_list.setAdapter(new RecommandListAdapter(getActivity().getApplicationContext(), AppDataBase.instance.articleDao().getAllArticle()));
        RegisterListener();
        return view;
    }

     public void RegisterListener(){
         ImageButton study_plan = view.findViewById(R.id.studyplan_button);
         ImageButton class_button= view.findViewById(R.id.class_button);
         study_plan.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), StudyPlanActivity.class);
                 startActivity(intent);
             }
         });
         class_button.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent intent = new Intent(getActivity(), CourseTableActivity.class);
                 startActivity(intent);
             }
         });
     }


    }
