package com.example.match.Entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * 回复表
 */
@Entity
public class Comment {
    @PrimaryKey(autoGenerate = true)
    private int comment_id;//评论ID
    @ColumnInfo
    private int article_id;//帖子ID
    @ColumnInfo
    private int user_id;//用户ID
    @ColumnInfo
    private String content;//评论内容
    @ColumnInfo
    private int up_id;//评论用户ID(实现评论，回复)
    @ColumnInfo
    private boolean is_level_one;//是否是一级回复
    @ColumnInfo
    private int reply_id;//回复评论的ID

    public int getReply_id() {
        return reply_id;
    }

    public void setReply_id(int reply_id) {
        this.reply_id = reply_id;
    }

    public int getComment_id() {
        return comment_id;
    }

    public boolean getIs_level_one() {
        return is_level_one;
    }

    public void setIs_level_one(boolean is_level_one) {
        this.is_level_one = is_level_one;
    }

    public void setComment_id(int comment_id) {
        this.comment_id = comment_id;
    }

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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUp_id() {
        return up_id;
    }

    public void setUp_id(int up_id) {
        this.up_id = up_id;
    }
}
