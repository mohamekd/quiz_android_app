package com.example.geoquiz;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CheatActivity extends Activity {


    public static final String EXTRA_ANSWER_IS_TRUE = "com.kood.geoquiz.answer_is_true";
    public static final String EXTRA_ANSWER_SHOWN ="com.kood.geoquiz.answer_shown";
    private static final String KEY_CHEATED = "cheated";

    private boolean mAnswerIsTrue;
    private TextView mAnswerTextView;
    private Button mShowAnswer;

    boolean isAnswerShown=false;

    private  void setAnswerIsShown(boolean isAnswerShown){
        Intent data = new Intent();
        data.putExtra(EXTRA_ANSWER_SHOWN,isAnswerShown);
        setResult(RESULT_OK,data);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);
        if (savedInstanceState != null) {
            isAnswerShown = savedInstanceState.getBoolean(KEY_CHEATED, false);
        }

        mAnswerIsTrue = getIntent().getBooleanExtra(EXTRA_ANSWER_IS_TRUE, false);

        mAnswerTextView = findViewById(R.id.answerTextView);

        // Answer will not be shown until the user presses the button

        Log.d("at_before_click",""+isAnswerShown);
        setAnswerIsShown(isAnswerShown);

        mShowAnswer = findViewById(R.id.showAnswerButton);
        mShowAnswer.setOnClickListener(v -> {
            if (mAnswerIsTrue) {
                mAnswerTextView.setText(R.string.true_button);
            } else {
                mAnswerTextView.setText(R.string.false_button);
            }
            isAnswerShown=true;
            setAnswerIsShown(isAnswerShown);
        });



    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(KEY_CHEATED, isAnswerShown);
        Log.d("at_save_instance",""+isAnswerShown);
    }
}