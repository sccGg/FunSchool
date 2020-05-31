package com.example.match.Adapter;


import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.example.match.AppDataBase;
import com.example.match.Compent.CircleDrawable;
import com.example.match.Entity.Article;
import com.example.match.Entity.User;
import com.example.match.MainActivity;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.util.List;
import java.util.zip.Inflater;

public class ArticleListAdapter extends BaseAdapter {
    private List<Article> article_data;
  private Activity activity;
    public ArticleListAdapter(List<Article> article_data, Activity activity) {
        this.article_data = article_data;
        this.activity=activity;
    }

    @Override
    public int getCount() {
        return article_data.size();
    }

    @Override
    public Object getItem(int position) {
        return article_data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return article_data.get(position).getArticle_id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArticleHolder articleHolder;
        if(convertView==null){
            convertView= View.inflate(activity,R.layout.article_list_item,null);
            articleHolder=new ArticleHolder();
            articleHolder.username=convertView.findViewById(R.id.item_username);
            articleHolder.content=convertView.findViewById(R.id.item_content);
            articleHolder.time=convertView.findViewById(R.id.article_time);
            articleHolder.imageView=convertView.findViewById(R.id.head_image);
            convertView.setTag(articleHolder);
        }else{
            articleHolder= (ArticleHolder) convertView.getTag();
        }
        User user = AppDataBase.instance.userDao().getUserById(article_data.get(position).getUser_id());
        articleHolder.username.setText(user.getName());
        articleHolder.time.setText(article_data.get(position).getTime());
        articleHolder.content.setText(article_data.get(position).getContent());
        articleHolder.imageView.setImageBitmap(ImageTool.ByteToBimMap(user.getHead()));
        return convertView;
    }
}
    class ArticleHolder{
     TextView username;
     TextView content;
     TextView time;
     ImageView imageView;
}
