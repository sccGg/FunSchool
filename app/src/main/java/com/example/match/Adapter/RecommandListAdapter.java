package com.example.match.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.match.AppDataBase;
import com.example.match.Entity.Article;
import com.example.match.Entity.User;
import com.example.match.R;
import com.example.match.Tool.ImageTool;

import java.util.List;

public class RecommandListAdapter extends RecyclerView.Adapter<RecommandHolder> {
    private Context context;
    private List<Article> articleList;
    private View view;

    public RecommandListAdapter(Context context,List articleList) {
        this.context = context;
        this.articleList=articleList;
    }


    @NonNull
    @Override
    public RecommandHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view= View.inflate(context,R.layout.recommand_list_item,null);
        return new RecommandHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommandHolder holder, int position) {
        holder.title.setText(articleList.get(position).getTitle());
        User user = AppDataBase.instance.userDao().getUserById(articleList.get(position).getUser_id());
        holder.author.setText(user.getName());
        holder.banner.setImageBitmap(ImageTool.ByteToBimMap(user.getHead()));
    }

    @Override
    public int getItemCount() {
        if(articleList.size()!=0){
            if(articleList.size()>4) {
                return 4;
            }
            return articleList.size();
        }
        return 0;
    }
}

    class RecommandHolder extends RecyclerView.ViewHolder{
         ImageButton banner;
         TextView title;
         TextView author;
        public RecommandHolder(@NonNull View itemView) {
            super(itemView);
            banner=itemView.findViewById(R.id.recommand_banner);
            title=itemView.findViewById(R.id.recommand_title);
            author=itemView.findViewById(R.id.recommand_author);
        }
    }
