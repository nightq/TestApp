package com.example.nightq.testapp;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.first_charge_dot_lottie_anim)
    LottieAnimationView firstChargeDotLottieAnim;
    @BindView(R.id.layout)
    FrameLayout layout;
    @BindView(R.id.imageview)
    ImageView imageview;
    @BindView(R.id.imageview1)
    ImageView imageview1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageview.getDrawable();
        animationDrawable.setOneShot(false);
        animationDrawable.start();
        AnimationDrawable animationDrawable1 = (AnimationDrawable) imageview1.getDrawable();
        animationDrawable1.setOneShot(false);
        animationDrawable1.start();
    }


    @OnClick(R.id.layout)
    public void onViewClicked() {

        playTitleAnimation();

    }

    private void playTitleAnimation() {
//        firstChargeDotLottieAnim.setAnimation("gift.json");
//        firstChargeDotLottieAnim.setAnimation("first_charge_type_item_animation.json");
//        firstChargeDotLottieAnim.setAnimation("first_charge_item_line.json");
        firstChargeDotLottieAnim.setAnimation("first_charge_item_light.json");
//        firstChargeDotLottieAnim.setSpeed(0.5f);
//        firstChargeDotLottieAnim.addAnimatorListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        firstChargeDotLottieAnim.playAnimation();
    }
}
