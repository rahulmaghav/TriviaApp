package com.example.triviaapp.database.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.triviaapp.model.UserDataModel;

import java.util.List;

//interface containing methods to interact with database
@Dao
public interface UserDataDao {

    //inserts user data
    //returns id of user
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insert(UserDataModel userDataModel);

    @Query("Select * from UserDataModel")
    List<UserDataModel> getAllUserData();

}
