package com.example.triviaapp.feature.thirdpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.triviaapp.R;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.feature.secondpage.SecondPageActivity;
import com.example.triviaapp.feature.summary.SummaryActivity;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.QuestionModel;

import java.util.ArrayList;

public class ThirdPageActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<QuestionModel> mQuestionModelArrayList;
    private QuestionModel mQuestionModel;
    private ArrayList<AnswerModel> mAnswerModelArrayList;
    private ArrayList<String> mSelectedAnswersList;

    private TextView mTextViewQuestionName;
    private LinearLayout multiCheckBoxContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third_page);

        mSelectedAnswersList = new ArrayList<>();
        if (getIntent() != null)
        {
            Bundle bundle = getIntent().getBundleExtra(KEY_BUNDLE_DATA);
            if(bundle != null)
            {
                mQuestionModelArrayList = bundle.getParcelableArrayList(QUESTION_LIST_DATA);
                mAnswerModelArrayList = bundle.getParcelableArrayList(ANSWER_LIST_DATA);
            }
        }

        initViews();
        setDataToViews();
    }

    private void initViews()
    {
        mTextViewQuestionName = findViewById(R.id.tv_question_name);

        multiCheckBoxContainer = findViewById(R.id.ll_multi_check_box_container);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(this);

    }

    private void setDataToViews()
    {
        if(mQuestionModelArrayList != null)
        {
            mQuestionModel = mQuestionModelArrayList.get(2);
            if(mQuestionModel != null)
            {
                ArrayList<String> optionList = mQuestionModel.getOptions();
                mTextViewQuestionName.setText(mQuestionModel.getQuestion_name());

                for (int i = 0 ; i < optionList.size() ; i++)
                {
                    View subView = getLayoutInflater().inflate(R.layout.layout_checkbox, null);
                    final CheckBox checkBox = (CheckBox) subView.findViewById(R.id.btn_checkbox);
                    multiCheckBoxContainer.addView(subView);

                    final String checkBoxValue = optionList.get(i);
                    checkBox.setText(checkBoxValue);

                    checkBox.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            if(((CheckBox) v).isChecked()){

                                mSelectedAnswersList.add(checkBoxValue);
                            }
                            else {

                                mSelectedAnswersList.remove(checkBoxValue);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button_next:

                String selectedAnswer = "";
                for (int i = 0 ; i < mSelectedAnswersList.size() ; i++)
                {
                    if(TextUtils.isEmpty(selectedAnswer))
                    {
                        selectedAnswer = mSelectedAnswersList.get(i);
                    }
                    else {
                        selectedAnswer = selectedAnswer + "," + mSelectedAnswersList.get(i);
                    }
                }

                AnswerModel answerModel = new AnswerModel();

                answerModel.setAnswer(selectedAnswer);
                answerModel.setQuestion_id(mQuestionModel.getId());
                answerModel.setQuestion_name(mQuestionModel.getQuestion_name());
                answerModel.setQuestion_type(mQuestionModel.getQuestion_type());

                mAnswerModelArrayList.add(answerModel);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(ANSWER_LIST_DATA, mAnswerModelArrayList);
                switchActivity(SummaryActivity.class, bundle);

                break;
        }
    }
}
