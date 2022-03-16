package com.christiencdev.quiznum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    //define necessary component variables
    private ImageView imageview;

    //create objects from animation class'
    Animation animationImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //link component variables to ids
        imageview = findViewById(R.id.imageView);

        //will load the animations we created in anim
        animationImage = AnimationUtils.loadAnimation(this, R.anim.image_animation);

        //we start the animation
        imageview.setAnimation(animationImage);

        //Then we open main screen after 5 secs (4secs anim, 1 sec pause)
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
        }.start();
    }
}