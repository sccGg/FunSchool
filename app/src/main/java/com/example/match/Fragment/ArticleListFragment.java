package com.example.match.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.match.Activity.ArticleDisplayActivity;
import com.example.match.Activity.ArticleEditActivity;
import com.example.match.Adapter.ArticleListAdapter;
import com.example.match.AppDataBase;
import com.example.match.Entity.Article;
import com.example.match.MainActivity;
import com.example.match.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleListFragment extends Fragment {
    private ListView article_list;
    private List<Article> article_data;
    private ImageButton back_index;
    private ImageButton addArticle_button;
    private View view;
    public ArticleListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_article_list, container, false);
        loadData(view);
        this.view=view;
        return view;
    }
    public void loadData(View view){
        article_list = view.findViewById(R.id.article_list);
        // article_data = AppDataBase.instance.articleDao().getAllArticle();
        ArticleListAdapter articleListAdapter = new ArticleListAdapter(AppDataBase.instance.articleDao().getAllArticle(),getActivity());
        article_list.setAdapter(articleListAdapter);
        back_index=view.findViewById(R.id.back_index);
        addArticle_button=view.findViewById(R.id.add_article);
        Register();
    }
    //注册监听
    public void Register(){
        back_index.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        addArticle_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),ArticleEditActivity.class);
                startActivity(intent);
            }
        });
        article_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), ArticleDisplayActivity.class);
                intent.putExtra("article_id",id);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if(!hidden){
            loadData(view);
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loadData(view);
    }
}
