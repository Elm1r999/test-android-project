package com.view.expo2020;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView totalQs, correctAns, wrongAns;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_result_layout);

        totalQs = findViewById(R.id.totalQuestions);
        correctAns = findViewById(R.id.correctAnswers);
        wrongAns = findViewById(R.id.wrongAnswers);

        Intent i = getIntent();

        String questions = i.getStringExtra("total");
        String correct = i.getStringExtra("correct");
        String wrong = i.getStringExtra("wrong");

        totalQs.setText(questions);
        correctAns.setText(correct);
        wrongAns.setText(wrong);

    }
}
