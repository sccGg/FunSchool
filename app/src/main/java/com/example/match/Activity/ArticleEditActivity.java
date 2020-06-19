package com.example.match.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.match.AppDataBase;
import com.example.match.Entity.Article;
import com.example.match.Entity.User;
import com.example.match.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class ArticleEditActivity extends AppCompatActivity {
    private Button subbmit;
    private ImageButton return_button;
    private EditText title;
    private EditText content;
    private User user;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_article_edit);
        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }
        subbmit=findViewById(R.id.subbmit);
        return_button=findViewById(R.id.return_botton);
        title=findViewById(R.id.titleedit);
        content=findViewById(R.id.contentedit);
        user= AppDataBase.instance.userDao().getUserByAccount(getApplicationContext().getSharedPreferences("user", Context.MODE_PRIVATE).getString("username",""));
        RegisterButtonListen();
    }

    //注册按钮监听
    public void RegisterButtonListen(){
        subbmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                   article_check();
            }
        });
    }
    //文章提交检测
    public boolean article_check() {
        if (title.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "标题不能为空", Toast.LENGTH_LONG).show();
            return false;
        }
        if (content.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "请填写内容", Toast.LENGTH_LONG).show();
            return false;
        }
        Article article = new Article();
        article.setTitle(title.getText().toString());
        article.setContent(content.getText().toString());
        article.setUser_id(user.getUser_id());
        Date day=new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        article.setTime(df.format(day));
        AppDataBase.instance.articleDao().addArticle(article);
        Toast.makeText(getApplicationContext(),"发表成功",Toast.LENGTH_SHORT).show();
        this.finish();
        return true;
    }

}
