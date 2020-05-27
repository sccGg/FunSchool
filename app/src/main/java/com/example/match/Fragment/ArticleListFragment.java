package com.example.match.Fragment;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.match.Adapter.ArticleListAdapter;
import com.example.match.AppDataBase;
import com.example.match.Entity.Article;
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
    public ArticleListFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_article_list, container, false);
        article_list = view.findViewById(R.id.article_list);
        initdata();
       // article_data = AppDataBase.instance.articleDao().getAllArticle();
        ArticleListAdapter articleListAdapter = new ArticleListAdapter(article_data,getActivity());
        article_list.setAdapter(articleListAdapter);
        return view;
    }
    public void initdata(){
        article_data = new ArrayList<>();
        for(int i=0;i<10;i++){
            Article article = new Article();
            article.setUser_id(i);
            article.setContent("阿斯顿撒就卡掉拉丝机付款了啥分厘卡说服力按时付款了我，但是哦服务器hi哦发丝哦活动i干撒都我好好的噶啥");
            article_data.add(article);
        }
    }


}
