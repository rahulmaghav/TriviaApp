package com.example.triviaapp.feature.secondpage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.triviaapp.R;
import com.example.triviaapp.baseui.BaseActivity;
import com.example.triviaapp.feature.thirdpage.ThirdPageActivity;
import com.example.triviaapp.model.AnswerModel;
import com.example.triviaapp.model.QuestionModel;

import java.util.ArrayList;

public class SecondPageActivity extends BaseActivity implements View.OnClickListener {

    private ArrayList<QuestionModel> mQuestionModelArrayList;
    private QuestionModel mQuestionModel;
    private ArrayList<AnswerModel> mAnswerModelArrayList;
    private String mAnswerSelected = "";

    private TextView mTextViewQuestionName;
    private RadioGroup mRadioGroup;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_page);

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

        mRadioGroup = findViewById(R.id.rg_container);

        Button nextButton = findViewById(R.id.button_next);
        nextButton.setOnClickListener(this);

    }

    private void setDataToViews()
    {
        if(mQuestionModelArrayList != null)
        {
            mQuestionModel = mQuestionModelArrayList.get(1);
            if(mQuestionModel != null)
            {
                ArrayList<String> optionList = mQuestionModel.getOptions();
                mTextViewQuestionName.setText(mQuestionModel.getQuestion_name());

                ((RadioButton) findViewById(R.id.rb1)).setText(optionList.get(0));
                ((RadioButton) findViewById(R.id.rb2)).setText(optionList.get(1));
                ((RadioButton) findViewById(R.id.rb3)).setText(optionList.get(2));
                ((RadioButton) findViewById(R.id.rb4)).setText(optionList.get(3));

                mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {

                        if (checkedId == R.id.rb1) {

                            mAnswerSelected = ((RadioButton) findViewById(R.id.rb1)).getText().toString();

                        } else if (checkedId == R.id.rb2) {

                            mAnswerSelected = ((RadioButton) findViewById(R.id.rb2)).getText().toString();

                        }
                        else if (checkedId == R.id.rb3) {

                            mAnswerSelected = ((RadioButton) findViewById(R.id.rb3)).getText().toString();

                        } else if (checkedId == R.id.rb4) {

                            mAnswerSelected = ((RadioButton) findViewById(R.id.rb4)).getText().toString();

                        }
                    }
                });
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.button_next:

                AnswerModel answerModel = new AnswerModel();

                answerModel.setAnswer(mAnswerSelected);
                answerModel.setQuestion_id(mQuestionModel.getId());
                answerModel.setQuestion_name(mQuestionModel.getQuestion_name());
                answerModel.setQuestion_type(mQuestionModel.getQuestion_type());

                mAnswerModelArrayList.add(answerModel);

                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(QUESTION_LIST_DATA, mQuestionModelArrayList);
                bundle.putParcelableArrayList(ANSWER_LIST_DATA, mAnswerModelArrayList);
                switchActivity(ThirdPageActivity.class, bundle);

                break;
        }
    }
}
