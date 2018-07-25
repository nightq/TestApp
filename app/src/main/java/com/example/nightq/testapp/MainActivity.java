package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.verifyImg)
    ImageView verifyImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.verifyImg)
    public void onViewClicked() {
        verifyImg.setImageBitmap(VerifyFactory.getInstance().createBitmap());
        Log.e("nightq", "code = " + VerifyFactory.getInstance().getCode());
    }
}
