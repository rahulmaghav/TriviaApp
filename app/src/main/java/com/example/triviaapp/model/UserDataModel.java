package com.example.triviaapp.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class UserDataModel {

    @PrimaryKey(autoGenerate = true)
    private int user_id;

    private String submitted_on;

    @Ignore
    private ArrayList<AnswerModel> answerModelList;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getSubmitted_on() {
        return submitted_on;
    }

    public void setSubmitted_on(String submitted_on) {
        this.submitted_on = submitted_on;
    }

    public ArrayList<AnswerModel> getAnswerModelList() {
        return answerModelList;
    }

    public void setAnswerModelList(ArrayList<AnswerModel> answerModelList) {
        this.answerModelList = answerModelList;
    }
}
