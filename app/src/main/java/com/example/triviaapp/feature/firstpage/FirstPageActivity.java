package com.example.triviaapp.feature.firstpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.triviaapp.R;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.feature.secondpage.SecondPageActivity;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.QuestionModel;
import com.example.triviaapp.utils.DataUtils;

import java.util.ArrayList;

public class FirstPageActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<QuestionModel> mQuestionModelArrayList;
    private QuestionModel mQuestionModel;
    private ArrayList<AnswerModel> mAnswerModelArrayList;

    private TextView mTextViewQuestionName;
    private EditText mEditTextName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);

        mQuestionModelArrayList = DataUtils.getQuestionsData();
        mAnswerModelArrayList = new ArrayList<>();

        initViews();
        setDataToViews();

    }

    private void initViews()
    {
        mTextViewQuestionName = findViewById(R.id.tv_question_name);
        mEditTextName = findViewById(R.id.et_name);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(this);

    }

    private void setDataToViews()
    {
        if(mQuestionModelArrayList != null)
        {
            mQuestionModel = mQuestionModelArrayList.get(0);
            if(mQuestionModel != null)
            {
                mTextViewQuestionName.setText(mQuestionModel.getQuestion_name());
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button_next:

                AnswerModel answerModel = new AnswerModel();

                answerModel.setAnswer(mEditTextName.getText().toString().trim());
                answerModel.setQuestion_id(mQuestionModel.getId());
                answerModel.setQuestion_name(mQuestionModel.getQuestion_name());
                answerModel.setQuestion_type(mQuestionModel.getQuestion_type());

                mAnswerModelArrayList.add(answerModel);


                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(QUESTION_LIST_DATA, mQuestionModelArrayList);
                bundle.putParcelableArrayList(ANSWER_LIST_DATA, mAnswerModelArrayList);
                switchActivity(SecondPageActivity.class, bundle);

                break;
        }
    }
}
