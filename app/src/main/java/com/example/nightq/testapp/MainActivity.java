package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       final  LinearLayout layout = (LinearLayout) findViewById(R.id.layout);


        layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                ImageView imageView = new ImageView(MainActivity.this);
                imageView.setBackgroundColor(0xff888888);
                layout.addView(imageView, new LinearLayout.LayoutParams(dip2px(100), dip2px(20)));
            }
        }, 1000);

    }

    public int dip2px(int value) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return (int) scaledDensity * value;
    }

}
