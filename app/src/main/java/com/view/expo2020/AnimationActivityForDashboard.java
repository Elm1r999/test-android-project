package com.view.expo2020;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AnimationActivityForDashboard extends AppCompatActivity {

    TextView pagetitle, pagesubtitle;
    ImageView packagePlace;
    SeekBar packageRange;
    Animation atg, packageimg;
    Button btnQuizApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package);

        atg = AnimationUtils.loadAnimation(this, R.anim.atg);
        packageimg = AnimationUtils.loadAnimation(this, R.anim.packageimg);
        pagetitle = findViewById(R.id.pagetitle);
        pagesubtitle = findViewById(R.id.pagesubtitle);
        packagePlace = findViewById(R.id.packagePlace);
        packageRange = findViewById(R.id.packageRange);

        btnQuizApp = findViewById(R.id.btnTakeQuiz);

        btnQuizApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent a = new Intent(AnimationActivityForDashboard.this, ExpoQuizActivity.class);
                startActivity(a);
            }
        });

        packageRange.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 35){
                    pagetitle.setText("Starter Guy");
                    pagesubtitle.setText("The simply text be dummies too good and easier");
                    packagePlace.setImageResource(R.drawable.icstarter);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
                else if(progress == 75){
                    pagetitle.setText("Business Player");
                    pagesubtitle.setText("The simply text be dummies too good and easier");
                    packagePlace.setImageResource(R.drawable.icbusinessplayer);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
                else if(progress == 100){
                    pagetitle.setText("Legend of VIP");
                    pagesubtitle.setText("The simply text be dummies too good and easier");
                    packagePlace.setImageResource(R.drawable.icvip);

                    // pass animation
                    packagePlace.startAnimation(packageimg);
                    pagetitle.startAnimation(atg);
                    pagesubtitle.startAnimation(atg);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }
}
