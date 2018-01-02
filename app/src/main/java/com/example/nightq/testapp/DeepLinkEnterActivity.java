package com.example.nightq.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.callback.LMDLResultListener;
import com.microquation.linkedme.android.referral.LMError;
import com.microquation.linkedme.android.util.LinkProperties;

import butterknife.ButterKnife;

public class DeepLinkEnterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_deeplink);
        ButterKnife.bind(this);
        Log.e("nigthq", "DeepLinkEnterActivity onCreate");
    }

    @Override
    public void onStart() {
        super.onStart();
        String device_id = LinkedME.getInstance().getDeviceId();
        Log.e("nigthq", "device = " + device_id);
        Log.e("nigthq", "DeepLinkEnterActivity onStart");
    }

    // 添加此处目的是针对后台APP通过uri scheme唤起的情况，
    // 注意：即使不区分用户是否登录也需要添加此设置，也可以添加到基类中
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        Log.e(LinkedME.TAG, "DeepLinkEnterActivity onNewIntent");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinkedME.getInstance().setImmediate(true);
//        LinkedME.getInstance().setDeepLinkListener(new LMDLResultListener() {
//            @Override
//            public void dlResult(Intent handledIntent, LMError lmError) {
//                super.dlResult(handledIntent, lmError);
//                Log.e("nightq", "dlresult");
//            }
//
//            @Override
//            public void dlParams(LinkProperties linkProperties) {
//                super.dlParams(linkProperties);
//            }
//        });
        Log.e("nigthq", "DeepLinkEnterActivity onResume");
//        finish();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                finish();
                overridePendingTransition(0, 0);
            }
        }, 200);
    }
}
