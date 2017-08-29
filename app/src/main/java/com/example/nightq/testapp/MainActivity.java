package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.wepie.snake.lib.upload.UploadManager;
import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;

import butterknife.ButterKnife;

import static com.wepie.snake.lib.upload.data.config.UploadConfig.PlatformQiniu;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        UploadManager uploadManager = new UploadManager();
        uploadManager.upload("test path", PlatformQiniu, new CallBack<UploadResult>() {
            @Override
            public void onSuccess(UploadResult uploadResult) {
                Log.e("nightq", "MainActivity success = " + uploadResult);
            }

            @Override
            public void onFail(String msg) {
                Log.e("nightq", "MainActivity onFail = " + msg);
            }
        });
    }

}
