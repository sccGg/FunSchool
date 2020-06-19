package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.match.Entity.UserInfo;


@Dao
public interface UserInfoDao {
    @Update
    void updateUserInfo(UserInfo userInfo);
    @Insert
    void addUserInfo(UserInfo userInfo);
    @Query("SELECT * FROM userinfo WHERE user_id=:user_id")
    UserInfo getUserInfo(int user_id);
}
