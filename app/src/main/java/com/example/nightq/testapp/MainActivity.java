package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieView);

        lottieAnimationView.setAnimation("simple_test.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();
    }

}
