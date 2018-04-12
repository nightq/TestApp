package com.example.nightq.testapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.img1)
    RoundCornerImageView img1;
    @BindView(R.id.img2)
    RoundCornerImageView img2;
    @BindView(R.id.img3)
    RoundCornerImageView img3;
    @BindView(R.id.img4)
    RoundCornerImageView img4;
    @BindView(R.id.img5)
    RoundCornerImageView img5;
    @BindView(R.id.img6)
    RoundCornerImageView img6;
    @BindView(R.id.imgLayout)
    LinearLayout imgLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.imgLayout)
    public void onViewClicked() {
        RoundCornerImageView[] imgs = {
                img1, img2, img3, img4,
                img5, img6};

        Random random = new Random();

        for (int i = 0; i < imgs.length; i++) {

            imgs[i].leftBottomR = 10 + random.nextInt(100);
            imgs[i].leftTopR = 10 + random.nextInt(100);
            imgs[i].rightBottomR = 10 + random.nextInt(100);
            imgs[i].rightTopR = 10 + random.nextInt(100);

            Log.e("nightq", "leftBottomR = " + imgs[i].leftBottomR
                    + " leftTopR = " + imgs[i].leftTopR
                    + " rightBottomR = " + imgs[i].rightBottomR
                    + " rightTopR = " + imgs[i].rightTopR
            );

            imgs[i].invalidate();
        }
        imgLayout.requestLayout();
    }
}
