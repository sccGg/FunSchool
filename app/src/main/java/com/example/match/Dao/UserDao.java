package com.example.match.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


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
    //更新
    @Update
    void UpdateUser(User user);

    //通过账户获取用户信息
    @Query("SELECT * FROM user WHERE account=:ac")
    User getUserByAccount(String ac);

    //通过ID获取用户
    @Query("SELECT * FROM user WhERE user_id=:id")
    User getUserById(int id);

    //更新头像
    @Query("UPDATE user SET head =:head WHERE user_id=:user_id")
    void changeHead(int user_id,byte[] head);
}
