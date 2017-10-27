package com.example.nightq.testapp;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        final LottieAnimationView lottieAnimationView = (LottieAnimationView) findViewById(R.id.lottieView);

//        lottieAnimationView.setAnimation("simple_test.json");

        lottieAnimationView.setAnimation("test.json");
        lottieAnimationView.loop(true);
        lottieAnimationView.playAnimation();

        Log.e("nightq", "value = " + getResources().getDimension(R.dimen.home_game_endless_layout_h));
//        getResources().getConfiguration().screenHeightDp
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("nightq", "layout w = " + findViewById(R.id.layout).getWidth());
            }
        }, 1000);
    }

}
