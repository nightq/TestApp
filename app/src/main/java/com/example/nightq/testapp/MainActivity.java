package com.example.nightq.testapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image)
    ImageView image;

    public static final String[] channel = {

            "anzhi",
            "oppo",
            "vivo",
            "newTencent",
            "xiaomi",
            "xiaominew",
            "360",
            "yunos",
            "samsung",
            "meituxiuxiu",
            "youpinwei",
            "wandoujia",
            "smartisan",
            "letv",
            "baidu",
            "tencent",
            "official",
            "sogou",
            "huawei",
            "oppo",
            "yingyongbao"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    int index = 0;

    @OnClick(R.id.image)
    public void onViewClicked() {
        final String content = channel[index];
        String url = "http%3A%2F%2Ftcsdzz.com%2Fcommon_deep_link%2F%3Fmarket%3D" +
                content +
                "%26tags%3D%E8%B5%9B%E4%BA%8B%26jump_url%3Dsnake%3A%2F%2FraceActivity";
        ShareUtil.getEncodeBitmap(url, new ShareUtil.Runnable() {
            @Override
            public void run(@NonNull Bitmap bitmap) {
                image.setImageBitmap(bitmap);
                Log.e("nightq", "content = " + content);
            }
        });
        index ++;
        if (index == channel.length-1) {
            index = 0;
        }
    }
}
