package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.match.Entity.User;

import java.util.List;

@Dao
public interface UserDao {
    //获取所有用户信息
    @Query("SELECT * FROM user")
    List<User> getAllUser();

    //添加用户
    @Insert
    void addUser(User...users);

    //通过账户获取用户信息
    @Query("SELECT * FROM user WHERE account=:ac")
    User getUserByAccount(String ac);

    //通过ID获取用户
    @Query("SELECT * FROM user WhERE user_id=:id")
    User getUserById(int id);
}
