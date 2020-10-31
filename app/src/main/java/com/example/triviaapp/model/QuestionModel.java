package com.example.triviaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class QuestionModel implements Parcelable {

    private int id;
    private String question_name;
    private int question_type;
    private ArrayList<String> options;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion_name() {
        return question_name;
    }

    public void setQuestion_name(String question_name) {
        this.question_name = question_name;
    }

    public int getQuestion_type() {
        return question_type;
    }

    public void setQuestion_type(int question_type) {
        this.question_type = question_type;
    }

    public ArrayList<String> getOptions() {
        return options;
    }

    public void setOptions(ArrayList<String> options) {
        this.options = options;
    }

    public QuestionModel() {

    }

    protected QuestionModel(Parcel in) {
        id = in.readInt();
        question_name = in.readString();
        question_type = in.readInt();
        options = in.createStringArrayList();
    }

    public static final Creator<QuestionModel> CREATOR = new Creator<QuestionModel>() {
        @Override
        public QuestionModel createFromParcel(Parcel in) {
            return new QuestionModel(in);
        }

        @Override
        public QuestionModel[] newArray(int size) {
            return new QuestionModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(question_name);
        dest.writeInt(question_type);
        dest.writeStringList(options);
    }
}
