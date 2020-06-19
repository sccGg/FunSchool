package com.example.match.Activity;

import android.app.ProgressDialog;
import android.icu.text.Edits;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.match.Adapter.CommentListAdapter;
import com.example.match.AppDataBase;
import com.example.match.Compent.InputDialog;
import com.example.match.Entity.Article;
import com.example.match.Entity.Comment;
import com.example.match.Entity.User;
import com.example.match.Fragment.ReplayListDialog;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleDisplayActivity extends AppCompatActivity {
    private ArrayList<Comment> comments;
    private int article_id;
    private User look_user;
    private Article article;
    private InputDialog inputDialog;
    private ListView listView;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_article_display);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        article_id = Math.toIntExact(getIntent().getExtras().getLong("article_id"));
        article = AppDataBase.instance.articleDao().getArticleById(article_id);
        listView = findViewById(R.id.comment_list);
        TextView username=findViewById(R.id.display_username);
        TextView content = findViewById(R.id.display_content);
        ImageView head_image=findViewById(R.id.head_image);
        User author = AppDataBase.instance.userDao().getUserById(article.getUser_id());
        username.setText(author.getName());
        content.setText(article.getContent());
        head_image.setImageBitmap(ImageTool.ByteToBimMap(author.getHead()));
        look_user=AppDataBase.instance.userDao().getUserByAccount(getSharedPreferences("user",MODE_PRIVATE).getString("username",""));
         loadComment();
        RegisterLinstener();
    }
    //注册监听
    public void RegisterLinstener() {
        ImageButton back = findViewById(R.id.back_bst);
        Button comment_button = findViewById(R.id.comment_button);
        comment_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadDialog(article.getUser_id()).show(getSupportFragmentManager(), "");
            }

        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Comment comment = AppDataBase.instance.commentDao().getCommentByID(Math.toIntExact(id));
                loadDialog(comment).show(getSupportFragmentManager(),"");
            }
        });

    }
    //加载评论数据
    public void loadComment(){
        comments=(ArrayList<Comment>) AppDataBase.instance.commentDao().getAllCommentByUser(article_id,article.getUser_id(),true);
        listView.setAdapter(new CommentListAdapter(comments,this,true));
    }
    //回复框
    public InputDialog loadDialog(final Comment cmt){
        inputDialog = new InputDialog("请输入内容~", new InputDialog.SendBackListener() {
            @Override
            public void sendBack(String inputText, ProgressDialog progressDialog) {
                Comment comment = new Comment();
                comment.setContent(inputText);
                comment.setUser_id(look_user.getUser_id());
                comment.setArticle_id(article.getArticle_id());
                comment.setUp_id(cmt.getUser_id());
                comment.setIs_level_one(false);
                 comment.setReply_id(cmt.getComment_id());
                AppDataBase.instance.commentDao().addComment(comment);
                loadComment();
                progressDialog.cancel();
                inputDialog.dismiss();
                Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_LONG).show();
            }
        });
        return inputDialog;
    }
    //评论框
    public InputDialog loadDialog(final int user_id){
        inputDialog = new InputDialog("请输入内容~", new InputDialog.SendBackListener() {
            @Override
            public void sendBack(String inputText, ProgressDialog progressDialog) {
                Comment comment = new Comment();
                comment.setContent(inputText);
                comment.setUser_id(look_user.getUser_id());
                comment.setArticle_id(article.getArticle_id());
                comment.setUp_id(user_id);
                comment.setIs_level_one(true);
                AppDataBase.instance.commentDao().addComment(comment);
                loadComment();
                progressDialog.cancel();
                inputDialog.dismiss();
                Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_LONG).show();
            }
        });
        return inputDialog;
    }

}
