package com.example.triviaapp.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class AnswerModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private int user_id;
    private int question_id;
    private String answer;
    private String question_name;
    private int question_type;

    public AnswerModel(){

    }

    protected AnswerModel(Parcel in) {
        id = in.readInt();
        user_id = in.readInt();
        question_id = in.readInt();
        answer = in.readString();
        question_name = in.readString();
        question_type = in.readInt();
    }

    public static final Creator<AnswerModel> CREATOR = new Creator<AnswerModel>() {
        @Override
        public AnswerModel createFromParcel(Parcel in) {
            return new AnswerModel(in);
        }

        @Override
        public AnswerModel[] newArray(int size) {
            return new AnswerModel[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getQuestion_id() {
        return question_id;
    }

    public void setQuestion_id(int question_id) {
        this.question_id = question_id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(user_id);
        dest.writeInt(question_id);
        dest.writeString(answer);
        dest.writeString(question_name);
        dest.writeInt(question_type);
    }
}
