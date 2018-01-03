package com.example.nightq.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.indexing.LMUniversalObject;
import com.microquation.linkedme.android.util.LinkProperties;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;

/**
 * <p>中转页面</p>
 *
 * Created by LinkedME06 on 16/11/17.
 */

public class MiddleActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("nightq", "onBackPressed MiddleActivity this = " + this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0, 0);
        super.onCreate(savedInstanceState);
        Log.e(LinkedME.TAG, "onCreate: MiddleActivity is called." + this);
//        Toast.makeText(this, "MiddleActivity 被调用了", Toast.LENGTH_SHORT).show();
        //获取与深度链接相关的值
        LinkProperties linkProperties = getIntent().getParcelableExtra(LinkedME.LM_LINKPROPERTIES);
        LMUniversalObject lmUniversalObject = getIntent().getParcelableExtra(LinkedME.LM_UNIVERSALOBJECT);
        //LinkedME SDK初始化成功，获取跳转参数，具体跳转参数在LinkProperties中，和创建深度链接时设置的参数相同；

        String content = "null";
        if (linkProperties != null) {
            Log.i("LinkedME-Demo", "Channel " + linkProperties.getChannel());
            Log.i("LinkedME-Demo", "control params " + linkProperties.getControlParams());
            Log.e("LinkedME-Demo", "link(深度链接) " + linkProperties.getLMLink());
            //获取自定义参数封装成的HashMap对象
            HashMap<String, String> hashMap = linkProperties.getControlParams();

            //获取传入的参数
            String view = hashMap.get("View");
            String title = "";
            String shareContent = "";
            String url_path = "";
            content = "view = " + view
                    + " title = " + title
                    + " sharecontent = " + shareContent
                    + " urlpath = " + url_path;
            Log.e("nightq", content);
            //清除跳转数据，该方法理论上不需要调用，因Android集成方式各种这样，若出现重复跳转的情况，可在跳转成功后调用该方法清除参数
            //LinkedME.getInstance().clearSessionParams();
        }

        if (lmUniversalObject != null) {
            Log.i("LinkedME-Demo", "title " + lmUniversalObject.getTitle());
            Log.i("LinkedME-Demo", "control " + linkProperties.getControlParams());
            Log.i("ContentMetaData", "metadata " + lmUniversalObject.getMetadata());
        }
        Jumphelper.jumpParam = content;
        processJump();
        finish();
    }

    private void processJump () {
        JumpEvent jumpEvent = new JumpEvent();
        EventBus.getDefault().post(jumpEvent);
        if (jumpEvent == HomeActivity.lastEvent) {
            Log.e("nightq", "processJump event process");
            return;
        } else {
            Log.e("nightq", "processJump StartActivity");
            EventBus.getDefault().postSticky(jumpEvent);
            Intent intent = new Intent(this, StartActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
            overridePendingTransition(0, 0);
        }
    }

}
