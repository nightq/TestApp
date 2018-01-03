package com.example.nightq.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.microquation.linkedme.android.LinkedME;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;

public class StartActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("nightq", "onBackPressed StartActivity this = " + this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("gnihtq", "HomeActivity ex = " + HomeActivity.isexist);
                startActivity(new Intent(StartActivity.this, HomeActivity.class));
                finish();
            }
        }, 100);
        Log.e(LinkedME.TAG, "StartActivity onCreate this = " + this);
    }

    // 添加此处目的是针对后台APP通过uri scheme唤起的情况，
    // 注意：即使不区分用户是否登录也需要添加此设置，也可以添加到基类中
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        Log.e(LinkedME.TAG, "StartActivity onNewIntent this = " + this);
    }

}
