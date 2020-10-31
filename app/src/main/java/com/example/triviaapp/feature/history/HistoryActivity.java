package com.example.triviaapp.feature.history;


import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.R;
import com.example.triviaapp.adapter.HistoryRecyclerAdapter;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.database.TriviaDatabase;
import com.example.triviaapp.database.dao.AnswerDao;
import com.example.triviaapp.database.dao.UserDataDao;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.UserDataModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HistoryActivity extends BaseActivity {

    private HistoryRecyclerAdapter mHistoryRecyclerAdapter;
    private TextView mTextViewEmpty;
    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerViewHistory;

    private UserDataDao mUserDataDao;
    private AnswerDao mAnswerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        TriviaDatabase triviaDatabase = TriviaDatabase.getInstance(getApplicationContext());
        mUserDataDao=triviaDatabase.userDataDao();
        mAnswerDao = triviaDatabase.answerDao();


        mTextViewEmpty = findViewById(R.id.tv_empty);
        mProgressBar = findViewById(R.id.progress_bar);
        mRecyclerViewHistory = findViewById(R.id.rv_history);

        //sets layout manager for adapter
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        mRecyclerViewHistory.setLayoutManager(linearLayoutManager);

        //sets the adapter with required data
        mHistoryRecyclerAdapter = new HistoryRecyclerAdapter(this);
        mRecyclerViewHistory.setAdapter(mHistoryRecyclerAdapter);

        new GetUserDataAsync().execute();
    }

    //async to fetch details of all users
    public class GetUserDataAsync extends AsyncTask<Void,Void,ArrayList<UserDataModel>>
    {
        @Override
        protected ArrayList<UserDataModel> doInBackground(Void... values) {

            List<UserDataModel> userDataModelList = mUserDataDao.getAllUserData();

            for (int i = 0 ; i < userDataModelList.size() ; i++)
            {
                ArrayList<AnswerModel> answerModelArrayList= ((ArrayList<AnswerModel>) mAnswerDao.getAllAnswersOfUser(userDataModelList.get(i).getUser_id()));
                Collections.sort(answerModelArrayList, new CompareAnswers());
                userDataModelList.get(i).setAnswerModelList(answerModelArrayList);
            }

            return ((ArrayList<UserDataModel>) userDataModelList);

        }

        //executed after doInBackground is completed
        @Override
        protected void onPostExecute(ArrayList<UserDataModel> userDataModelArrayList) {

            mProgressBar.setVisibility(View.GONE);
            if(userDataModelArrayList != null && userDataModelArrayList.size() > 0)
            {
                mTextViewEmpty.setVisibility(View.GONE);
                mRecyclerViewHistory.setVisibility(View.VISIBLE);
                mHistoryRecyclerAdapter.onDataChanged(userDataModelArrayList);
            }
            else {
                mTextViewEmpty.setVisibility(View.VISIBLE);
                mRecyclerViewHistory.setVisibility(View.GONE);
            }
        }

    }

    /**
     * comparator used for sorting Answers according to its question id
     */
    private class CompareAnswers implements Comparator<AnswerModel> {

        @Override
        public int compare(AnswerModel object1, AnswerModel object2) {

            int question_id1 = object1.getQuestion_id();
            int question_id2 = object2.getQuestion_id();

            if (question_id1 < question_id2)
                return -1;
            else if (question_id1 > question_id2)
                return 1;
            else
                return 0;
        }
    }
}
