package com.example.thi_30_10;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logo = findViewById(R.id.img_logo);

        AnimatorSet animationSet = (AnimatorSet) AnimatorInflater.loadAnimator(MainActivity.this, R.animator.hieu_ung);
        animationSet.setTarget(logo);
        animationSet.start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,TrangChu.class));
            }
        },3000);

    }
}