package com.example.match.Adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.match.AppDataBase;
import com.example.match.Compent.InputDialog;
import com.example.match.Entity.Comment;
import com.example.match.Entity.User;
import com.example.match.Fragment.ReplayListDialog;
import com.example.match.MainActivity;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.util.ArrayList;

public class CommentListAdapter extends BaseAdapter {
    private ArrayList<Comment> comments;
    private Activity activity;
    private boolean is_level_one;
    public CommentListAdapter(ArrayList<Comment> comments, Activity activity,boolean is_level_one) {
        this.comments = comments;
        this.activity = activity;
        this.is_level_one=is_level_one;
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
        return comments.get(position).getComment_id();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        CommentHolder commentHolder;
        if(convertView==null){
            convertView=View.inflate(activity.getApplicationContext(), R.layout.comment_list_item,null);
            commentHolder = new CommentHolder();
            commentHolder.comment_username=convertView.findViewById(R.id.comment_username);
            commentHolder.comment_cmt=convertView.findViewById(R.id.comment_cmt);
            commentHolder.head_image=convertView.findViewById(R.id.comment_head);
            commentHolder.look_reply_button=convertView.findViewById(R.id.look_reply_button);
            convertView.setTag(commentHolder);
        }else{
            commentHolder= (CommentHolder) convertView.getTag();
        }
        User user = AppDataBase.instance.userDao().getUserById(comments.get(position).getUser_id());
        commentHolder.comment_username.setText(user.getName());
        commentHolder.comment_cmt.setText(comments.get(position).getContent());
        commentHolder.head_image.setImageBitmap(ImageTool.ByteToBimMap(user.getHead()));
        if(!is_level_one){
            commentHolder.look_reply_button.setVisibility(View.GONE);
        }else {
            int count = AppDataBase.instance.commentDao().getAllReply(comments.get(position).getArticle_id(), comments.get(position).getUser_id(), false,comments.get(position).getComment_id()).size();
            commentHolder.look_reply_button.setVisibility(View.VISIBLE);
            if (count == 0) {
                commentHolder.look_reply_button.setText("回复");
            } else {
                commentHolder.look_reply_button.setText("查看" + count + "条回复>");
            }
            commentHolder.look_reply_button.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View v) {
                    AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
                    new ReplayListDialog(comments.get(position).getArticle_id(),comments.get(position).getUser_id(), Math.toIntExact(getItemId(position))).show(((AppCompatActivity) activity).getSupportFragmentManager(),"");
                }
            });
        }

        return convertView;
    }
}
    class CommentHolder{
    TextView comment_username;
    TextView comment_time;
    TextView comment_cmt;
    Button look_reply_button;
    ImageView head_image;
    }
