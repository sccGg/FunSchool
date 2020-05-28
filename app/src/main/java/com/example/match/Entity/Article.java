package com.example.match.Entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Article {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    private int article_id;//帖子ID
    @ColumnInfo
    private int user_id;//用户ID
    @ColumnInfo
    private String title;//标题
    @ColumnInfo
    private String content;//内容
    @ColumnInfo
    private String time;//发表时间

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
