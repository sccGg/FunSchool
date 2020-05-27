package com.example.match.Adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.match.Entity.Comment;
import com.example.match.R;

import java.util.ArrayList;

public class CommentListAdapter extends BaseAdapter {
    private ArrayList<Comment> comments;
    private Activity activity;

    public CommentListAdapter(ArrayList<Comment> comments, Activity activity) {
        this.comments = comments;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommentHolder commentHolder;
        if(convertView==null){
            convertView=View.inflate(activity.getApplicationContext(), R.layout.comment_list_item,null);
            commentHolder = new CommentHolder();
            commentHolder.comment_username=convertView.findViewById(R.id.comment_username);
            commentHolder.comment_cmt=convertView.findViewById(R.id.comment_cmt);
            convertView.setTag(commentHolder);
        }else{
            commentHolder= (CommentHolder) convertView.getTag();
        }
        commentHolder.comment_username.setText("罗志祥"+String.valueOf(comments.get(position).getUser_id()));
        commentHolder.comment_cmt.setText(comments.get(position).getContent());
        return convertView;
    }
}
    class CommentHolder{
    TextView comment_username;
    TextView comment_time;
    TextView comment_cmt;
    }
