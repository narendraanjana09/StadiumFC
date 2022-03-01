package com.nsa.stadiumfc.db;



import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.nsa.stadiumfc.models.UserModel;

import java.util.List;

@Dao
public interface UserDao {

    @Insert
    void insert(UserModel userModel);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * FROM user_table")
    LiveData<UserModel> getUser();

}