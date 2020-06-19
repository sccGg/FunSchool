package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.match.Entity.Article;

import java.util.List;

@Dao
public interface ArticleDao {

    @Insert
    void addArticle(Article article);

    @Query("SELECT * FROM article WHERE article_id=:id")
    Article getArticleById(int id);

    @Query("SELECT * FROM article  ORDER BY article_id desc")
    List<Article> getAllArticle();
}
