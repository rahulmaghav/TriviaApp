package com.example.triviaapp.feature.summary;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triviaapp.R;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.database.TriviaDatabase;
import com.example.triviaapp.database.dao.AnswerDao;
import com.example.triviaapp.database.dao.UserDataDao;
import com.example.triviaapp.feature.firstpage.FirstPageActivity;
import com.example.triviaapp.feature.history.HistoryActivity;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.QuestionModel;
import com.example.triviaapp.model.UserDataModel;
import com.example.triviaapp.utils.DataUtils;

import java.util.ArrayList;

public class SummaryActivity extends BaseActivity {

    private ArrayList<AnswerModel> mAnswerModelArrayList;

    private TextView mTextViewName;
    private LinearLayout mLLContainer;

    private UserDataDao mUserDataDao;
    private AnswerDao mAnswerDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        TriviaDatabase triviaDatabase = TriviaDatabase.getInstance(getApplicationContext());
        mUserDataDao=triviaDatabase.userDataDao();
        mAnswerDao = triviaDatabase.answerDao();

        if (getIntent() != null)
        {
            Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE_DATA);
            if(bundle != null)
            {
                mAnswerModelArrayList = bundle.getParcelableArrayList(ANSWER_LIST_DATA);
            }
        }

        initViews();
        setDataToViews();
    }

    private void initViews()
    {
        mTextViewName = findViewById(R.id.tv_name);
        mLLContainer = findViewById(R.id.ll_container);

        Button finishButton = findViewById(R.id.layout_finish);
        finishButton.setText(getString(R.string.finish));
        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                UserDataModel userDataModel = new UserDataModel();
                userDataModel.setSubmitted_on(DataUtils.getDateTime());

                new InsertAsyncTask().execute(userDataModel);
            }
        });


        Button historyButton = findViewById(R.id.layout_history);
        historyButton.setText(getString(R.string.history));
        historyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switchActivity(HistoryActivity.class);
            }
        });

    }

    private void setDataToViews()
    {
        mTextViewName.setText(String.format(getString(R.string.hello), mAnswerModelArrayList.get(0).getAnswer()));

        for (int i = 1 ; i < mAnswerModelArrayList.size() ; i++)
        {
            View subView = getLayoutInflater().inflate(R.layout.layout_answer_selected, null);

            final TextView textViewQuestion = subView.findViewById(R.id.tv_question);
            textViewQuestion.setText(mAnswerModelArrayList.get(i).getQuestion_name());

            final TextView textViewAnswer = subView.findViewById(R.id.tv_answer);
            textViewAnswer.setText(getString(R.string.answer) + mAnswerModelArrayList.get(i).getAnswer());

            mLLContainer.addView(subView);
        }
    }


    public class InsertAsyncTask extends AsyncTask<UserDataModel,Void,Long>
    {

        //inserts the data entered by user to database
        @Override
        protected Long doInBackground(UserDataModel... userDataModels) {

            //inserts data
            //returns id of inserted row
            long value = mUserDataDao.insert(userDataModels[0]);

            for (int i = 0 ; i < mAnswerModelArrayList.size() ; i++)
            {
                mAnswerModelArrayList.get(i).setUser_id(((int) value));
            }
            mAnswerDao.insertAll(mAnswerModelArrayList);

            return value;
        }

        //executed after insertion process is over
        @Override
        protected void onPostExecute(Long value) {

            //if insertion is successful then
            //shows a success message
            if(value > 0)
            {
                Toast.makeText(SummaryActivity.this,getString(R.string.success),Toast.LENGTH_SHORT).show();
            }

            //if failure, then shows failure message
            else {
                Toast.makeText(SummaryActivity.this,getString(R.string.failure),Toast.LENGTH_SHORT).show();
            }

            finishAffinity();
            switchActivity(FirstPageActivity.class);
        }

    }

}
