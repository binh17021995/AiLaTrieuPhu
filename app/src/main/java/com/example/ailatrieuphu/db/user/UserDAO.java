package com.example.ailatrieuphu.db.user;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.ailatrieuphu.model.User;

import java.util.List;


@Dao
public interface UserDAO {


    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> getListUser();


    @Query("SELECT * FROM user where username= :username")
    List<User>  checkUser(String username);


    @Update
    void updateUser(User user);

    @Delete
    void deleteUser(User user);


}
