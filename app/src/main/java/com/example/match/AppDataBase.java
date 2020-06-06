package com.example.match;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.match.Dao.ArticleDao;
import com.example.match.Dao.CommentDao;
import com.example.match.Dao.StudyPlanDao;
import com.example.match.Dao.UserDao;
import com.example.match.Dao.UserInfoDao;
import com.example.match.Entity.Article;
import com.example.match.Entity.Comment;
import com.example.match.Entity.StudyPlan;
import com.example.match.Entity.User;
import com.example.match.Entity.UserInfo;


@Database(entities = {User.class,Article.class, Comment.class, StudyPlan.class, UserInfo.class},version = 4)
public abstract class AppDataBase extends RoomDatabase {
    public static AppDataBase instance;
    public static AppDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context, AppDataBase.class, "app_database").allowMainThreadQueries().build();
        }
        return instance;
    }
    public abstract UserDao userDao();
    public abstract ArticleDao articleDao();
    public abstract CommentDao commentDao();
    public abstract StudyPlanDao studyPlanDao();
    public abstract UserInfoDao userInfoDao();
}
