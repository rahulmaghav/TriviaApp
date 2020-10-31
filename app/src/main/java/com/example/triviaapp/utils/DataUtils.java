package com.example.triviaapp.utils;


import com.example.triviaapp.data.AppConstant;
import com.example.triviaapp.model.QuestionModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class DataUtils implements AppConstant {


    public static ArrayList<QuestionModel> getQuestionsData()
    {
        ArrayList<QuestionModel> questionModelArrayList = new ArrayList<>();


        QuestionModel questionModel1 = new QuestionModel();

        questionModel1.setId(1);
        questionModel1.setQuestion_name("What is your name?");
        questionModel1.setQuestion_type(QUESTION_TYPE_TEXT);

        ArrayList<String> optionsList1 = new ArrayList<>();
        questionModel1.setOptions(optionsList1);

        questionModelArrayList.add(questionModel1);


        QuestionModel questionModel2 = new QuestionModel();

        questionModel2.setId(2);
        questionModel2.setQuestion_name("Who is the best cricketer in the world?");
        questionModel2.setQuestion_type(QUESTION_TYPE_SINGLE_CHOICE);

        ArrayList<String> optionsList2 = new ArrayList<>();
        optionsList2.add("Sachin Tendulkar");
        optionsList2.add("Virat Kohli");
        optionsList2.add("Adam Gilchrist");
        optionsList2.add("Jaques Kallis");
        questionModel2.setOptions(optionsList2);

        questionModelArrayList.add(questionModel2);



        QuestionModel questionModel3 = new QuestionModel();

        questionModel3.setId(3);
        questionModel3.setQuestion_name("What are the colors in the Indian national flag?");
        questionModel3.setQuestion_type(QUESTION_TYPE_MULTI_CHOICE);

        ArrayList<String> optionsList3 = new ArrayList<>();
        optionsList3.add("White");
        optionsList3.add("Yellow");
        optionsList3.add("Orange");
        optionsList3.add("Green");
        questionModel3.setOptions(optionsList3);

        questionModelArrayList.add(questionModel3);



        return questionModelArrayList;
    }


    public static String getDateTime() {

        long currentTime = getCurrentLocalTimeInSeconds() * 1000;

        return getDate(currentTime) + " \u2022 " + getTime(currentTime);

    }

    /**
     * Method use to return current timestamp(in Seconds)
     * @return
     */
    public static long getCurrentLocalTimeInSeconds()
    {
        long currentTimeSeconds= System.currentTimeMillis()/1000;
        TimeZone tz = TimeZone.getDefault();
        Date now = new Date();
        int offsetFromUtc = tz.getOffset(now.getTime()) / 1000;
        currentTimeSeconds=currentTimeSeconds + offsetFromUtc;

        return currentTimeSeconds;
    }


    public static String getTime(Long timestamp) {

        java.sql.Date date = new java.sql.Date(timestamp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("H:mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date);

    }

    /**
     * Get date in format: d MMM yyyy
     *
     * @return
     */
    public static String getDate(Long timeStamp) {

        java.sql.Date date = new java.sql.Date(timeStamp);

        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(date).toUpperCase();
    }

}
