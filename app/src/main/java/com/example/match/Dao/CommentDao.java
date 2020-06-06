package com.example.match.Dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.match.Entity.Comment;

import java.util.List;

@Dao
public interface CommentDao {

    @Query("SELECT * FROM comment WHERE article_id=:article_id")
    List<Comment> getAllCommentByArticleId(int article_id);
    @Insert
    void addComment(Comment comment);
    @Query("SELECT * FROM comment WHERE article_id=:article_id AND up_id=:up_id AND is_level_one=:is_level_one")
    List<Comment> getAllCommentByUser(int article_id,int up_id,boolean is_level_one);

    @Query("SELECT * FROM comment WHERE article_id=:article_id AND up_id=:up_id AND is_level_one=:is_level_one AND reply_id=:reply_id")
    List<Comment> getAllReply(int article_id,int up_id,boolean is_level_one,int reply_id);
    @Query("SELECT * FROM comment WHERE comment_id=:comment_id")
    Comment getCommentByID(int comment_id);
}
