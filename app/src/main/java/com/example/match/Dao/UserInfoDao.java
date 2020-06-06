package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Update;

import com.example.match.Entity.UserInfo;


@Dao
public interface UserInfoDao {
    @Update
    void updateUserInfo(UserInfo userInfo);

}
