package com.example.triviaapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.triviaapp.data.AppConstant;
import com.example.triviaapp.database.dao.AnswerDao;
import com.example.triviaapp.database.dao.UserDataDao;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.UserDataModel;

@Database(entities = {AnswerModel.class, UserDataModel.class},version = 1)
public abstract class TriviaDatabase extends RoomDatabase implements AppConstant {

    private static TriviaDatabase triviaDatabase;

    // Database name to be used
    private static final String NAME = DATABASE_NAME;

    //returns AnswerDao Object
    public abstract AnswerDao answerDao();

    //returns UserDataDao Object
    public abstract UserDataDao userDataDao();

    //returns object of TriviaDatabase class
    public static TriviaDatabase getInstance(Context context)
    {
        if(triviaDatabase==null)
        {
            triviaDatabase= Room.databaseBuilder(context,TriviaDatabase.class, TriviaDatabase.NAME)
                    .build();
        }

        return triviaDatabase;

    }

}
