package com.view.expo2020;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.view.expo2020.Model.QuizQuestions;

public class ExpoQuizActivity extends AppCompatActivity {

    int progressValue = 0;

    CountDownTimer countDownTimer;

    int index = 0, score = 0, thisQuestion = 0, totalQuestion = 0, correctAnswer;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference questionsReference;

    ProgressBar progressBar;
    Button btnA, btnB, btnC, btnD;
    TextView txtQuestion, txtScore, txtTimer;
    int correct = 0;
    int wrong = 0;
    int total = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_quiz);

        btnA = findViewById(R.id.btnOptionA);
        btnB = findViewById(R.id.btnOptionB);
        btnC = findViewById(R.id.btnOptionC);
        btnD = findViewById(R.id.btnOptionD);

        txtQuestion = findViewById(R.id.txtTotalQuestions);
        txtTimer = findViewById(R.id.timeWatch);

        updateQuestion(); //call the question update method
        reverseTime(60, txtTimer); //give 60 secs to quess the answers, enable the Timewatch on textView
    }

    private void updateQuestion(){
        if(total > 10){
            //open the result Activity
            Intent intent = new Intent(ExpoQuizActivity.this, ResultActivity.class);
            intent.putExtra("Total: ", String.valueOf(total));
            intent.putExtra("Correct answers: ", String.valueOf(correct));
            intent.putExtra("Wrong answers: ", String.valueOf(wrong));
            startActitivty(intent);
        }
        else
            questionsReference = FirebaseDatabase.getInstance().getReference().child("Questions").child(String.valueOf(total));
            questionsReference.addValueEventListener(new ValueEventListener() {

                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                final QuizQuestions quizQuestions = dataSnapshot.getValue(QuizQuestions.class);
                txtQuestion.setText(quizQuestions.getQuestion());

                btnA.setText(quizQuestions.getAnswerA());
                btnB.setText(quizQuestions.getAnswerB());
                btnC.setText(quizQuestions.getAnswerC());
                btnD.setText(quizQuestions.getAnswerD());

                btnA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(btnA.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                        {
                            btnA.setBackgroundColor(Color.GREEN);
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    correct++;
                                    btnA.setBackgroundColor(Color.parseColor("03A9F4"));
                                    updateQuestion();
                                } }, 1500);
                        }
                        else {  //answer is wrong, check all the possible answers to show the correct one with green color
                            wrong++;
                            btnA.setBackgroundColor(Color.RED);

                            if(btnB.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                            {
                                btnA.setBackgroundColor(Color.GREEN);
                            }
                            else if(btnC.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                            {
                                btnA.setBackgroundColor(Color.GREEN);
                            }
                            else if(btnD.getText().toString().equals(quizQuestions.getCorrectAnswer())){
                                btnA.setBackgroundColor(Color.GREEN);
                            }

                            Handler handler = new Handler(); //replace all the colors with default and update the question
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    btnA.setBackgroundColor(Color.parseColor("03A9F4"));
                                    btnB.setBackgroundColor(Color.parseColor("03A9F4"));
                                    btnC.setBackgroundColor(Color.parseColor("03A9F4"));
                                    btnD.setBackgroundColor(Color.parseColor("03A9F4"));
                                    updateQuestion();
                                } }, 1500);
                            } }
                });

                    btnB.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(btnB.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                            {
                                btnB.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnB.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            }
                            else {  //answer is wrong, check all the possible answers to show the correct one with green color
                                wrong++;
                                btnB.setBackgroundColor(Color.RED);

                                if(btnA.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnB.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnC.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnB.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnD.getText().toString().equals(quizQuestions.getCorrectAnswer())){
                                    btnB.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler();  //replace all the colors with default and update the question
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnA.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnB.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnC.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnD.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            } }
                    });

                    btnC.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(btnC.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                            {
                                btnC.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnC.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            }
                            else {  //answer is wrong, check all the possible answers to show the correct one with green color
                                wrong++;
                                btnC.setBackgroundColor(Color.RED);

                                if(btnA.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnC.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnB.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnC.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnD.getText().toString().equals(quizQuestions.getCorrectAnswer())){
                                    btnC.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler(); //replace all the colors with default and update the question
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnA.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnB.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnC.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnD.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            } }
                    });


                    btnD.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(btnD.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                            {
                                btnD.setBackgroundColor(Color.GREEN);
                                Handler handler = new Handler();
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        correct++;
                                        btnD.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            }
                            else {  //answer is wrong, check all the possible answers to show the correct one with green color
                                wrong++;
                                btnD.setBackgroundColor(Color.RED);

                                if(btnA.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnD.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnB.getText().toString().equals(quizQuestions.getCorrectAnswer()))
                                {
                                    btnD.setBackgroundColor(Color.GREEN);
                                }
                                else if(btnC.getText().toString().equals(quizQuestions.getCorrectAnswer())){
                                    btnD.setBackgroundColor(Color.GREEN);
                                }

                                Handler handler = new Handler(); //replace all the colors with default and update the question
                                handler.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        btnA.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnB.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnC.setBackgroundColor(Color.parseColor("03A9F4"));
                                        btnD.setBackgroundColor(Color.parseColor("03A9F4"));
                                        updateQuestion();
                                    } }, 1500);
                            } }
                    });

        }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    public void reverseTime(int seconds, final TextView tView){
        new CountDownTimer(seconds * 1000 + 1000, 1000){

            public void onTick(long millisUntilFinished){
                int seconds = (int) (millisUntilFinished / 1000);
                int minutes = seconds / 60;
                seconds = seconds % 60;
                tView.setText(String.format("%02d", minutes) + " : " + String.format("%02d", seconds));
            }

            public void onFinish() {
                tView.setText("Completed.");

                Toast.makeText(ExpoQuizActivity.this, "Thank you for taking part in the quiz! Check your results below.", Toast.LENGTH_SHORT).show();

                Intent i = new Intent(ExpoQuizActivity.this, ResultActivity.class);
                i.putExtra("Total: ", String.valueOf(total));
                i.putExtra("Correct answers: ", String.valueOf(correct));
                i.putExtra("Wrong answers: ", String.valueOf(wrong));
                startActitivty(i);
            }
        }.start();
    }

}
