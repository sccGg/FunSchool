package com.example.match.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.match.Adapter.CommentListAdapter;
import com.example.match.Entity.Comment;
import com.example.match.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDisplayFragment extends Fragment {
    private ArrayList<Comment> comments;

    public ArticleDisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_article_display, container, false);
        initdata();
        ListView listView = view.findViewById(R.id.comment_list);
        listView.setAdapter(new CommentListAdapter(comments,getActivity()));
        return view;
    }

    public void initdata(){
        comments = new ArrayList<>();
        for(int i=0;i<10;i++){
            Comment comment = new Comment();
            comment.setUser_id(i);
            comment.setContent(i+"  会打死哦对hi哦啊是第哦啊是都i啥的");
            comments.add(comment);
        }
    }
}
