package com.example.triviaapp.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.triviaapp.model.AnswerModel;

import java.util.List;

//interface containing methods to interact with database
@Dao
public interface AnswerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<AnswerModel> answerModelList);

    @Query("select * from AnswerModel where user_id = :id ")
    List<AnswerModel> getAllAnswersOfUser(int id);

}
