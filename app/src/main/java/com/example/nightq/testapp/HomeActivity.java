package com.example.nightq.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.microquation.linkedme.android.LinkedME;
import com.microquation.linkedme.android.callback.LMLinkCreateListener;
import com.microquation.linkedme.android.indexing.LMUniversalObject;
import com.microquation.linkedme.android.referral.LMError;
import com.microquation.linkedme.android.util.LinkProperties;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Log.e("nightq", "onBackPressed HomeActivity this = " + this);
    }

    // 添加此处目的是针对后台APP通过uri scheme唤起的情况，
    // 注意：即使不区分用户是否登录也需要添加此设置，也可以添加到基类中
    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        Log.e(LinkedME.TAG, "HomeActivity onNewIntent");
    }

    public static JumpEvent lastEvent;
    public static boolean isexist;

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.edit_text)
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        isexist = true;
        ButterKnife.bind(this);
        Log.e(LinkedME.TAG, "HomeActivity onCreate = " + this);
        EventBus.getDefault().register(this);
        JumpEvent jumpEvent = EventBus.getDefault().getStickyEvent(JumpEvent.class);
        EventBus.getDefault().removeStickyEvent(JumpEvent.class);
        if (jumpEvent != null) {
            onJumpEvent(jumpEvent);
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinkedME.getInstance().setImmediate(true);
    }

    @Subscribe
    public void onJumpEvent(JumpEvent event) {
        // todo nightq now
        lastEvent = event;
        // do something
        Log.e("nightq", "onJumpEvent " + Thread.currentThread().getName() + " " + this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        isexist = false;
        EventBus.getDefault().unregister(this);
    }

    public void share() {
        /**创建深度链接*/
        //web服务器无法创建深度链接时,客户端可选择创建
        //深度链接属性设置
        LinkProperties properties = new LinkProperties();
        //渠道
        properties.setChannel("vivosdf");  //微信、微博、QQ
        //功能
        properties.setFeature("share");
        //标签
        properties.addTag("LinkedME");
        properties.addTag("Demo");
        properties.setStage("Live");
        //阶段
        //设置该h5_url目的是为了iOS点击右上角lkme.cc时跳转的地址，一般设置为当前分享页面的地址
        //客户端创建深度链接请设置该字段
        properties.setH5Url("https://linkedme.cc/h5/feature");
        //自定义参数,用于在深度链接跳转后获取该数据
        properties.addControlParameter("LinkedME", "test linked me");
        properties.addControlParameter("View", "test view");
        LMUniversalObject universalObject = new LMUniversalObject();
        universalObject.setTitle("测试的title");
        // 异步生成深度链接
        universalObject.generateShortUrl(this, properties, new LMLinkCreateListener() {
            //https://www.lkme.cc/AfC/idFsW02l7
            @Override
            public void onLinkCreate(final String url, LMError error) {
                if (error == null) {
                    Log.e("linkedme", "创建深度链接成功！创建的深度链接为：" + url + " editText = " + editText);
                    ((TextView)findViewById(R.id.edit_text)).setText(url);
                } else {
                    Log.i("linkedme", "创建深度链接失败！失败原因：" + error.getMessage());
                }
            }
        });
    }

//    @OnClick(R.id.button)
//    public void onViewClicked() {
//        share();
//    }
}
