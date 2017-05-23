package com.example.nightq.testapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.security.PrivateKey;

/**
 * Created by nightq
 * 游戏结束，过场动画，时长：3000
 */

public class GameOverTransitAnimView extends FrameLayout {
    private Context mContext;

    private ImageView starLightImg;
    private ImageView skyBgImg;
    private ImageView gameEndImg;
    private ImageView titleLightImg;
    private ImageView starImg;
    private ImageView starBlingImg;

    public GameOverTransitAnimView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;
        init();
    }

    private void init() {
        LayoutInflater.from(mContext).inflate(R.layout.ol_game_over_transit_anim_view, this);
        starLightImg = (ImageView) findViewById(R.id.star_light_img);
        skyBgImg = (ImageView) findViewById(R.id.sky_bg_img);
        gameEndImg = (ImageView) findViewById(R.id.game_end_img);
        titleLightImg = (ImageView) findViewById(R.id.title_light_img);
        starImg = (ImageView) findViewById(R.id.star_img);
        starBlingImg = (ImageView) findViewById(R.id.star_bling_img);
    }

    public void show() {

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarAnim();
            }
        }, 700l);


        postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarLightAnim();
            }
        }, 700l);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startSkyBgAnim();
            }
        }, 1900l);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startGameEndAnim();
            }
        }, 500l);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startTitleLightAnim();
            }
        }, 1250l);

        postDelayed(new Runnable() {
            @Override
            public void run() {
                startStarBlingAnim();
            }
        }, 1500l);
    }

    private void startStarAnim() {
        starImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -MainActivity.dp2pX(106), 0);
        translateAnimation.setFillBefore(false);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(150);

        ScaleAnimation scaleAnimation = new ScaleAnimation(1.6f, 1f, 1.6f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setFillBefore(false);
        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(150);

        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scaleAnimation);

        starImg.startAnimation(animationSet);
    }

    private void startStarLightAnim() {
        starLightImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -MainActivity.dp2pX(106), 0);
        translateAnimation.setFillBefore(false);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(150);

        animationSet.addAnimation(translateAnimation);

        starLightImg.startAnimation(animationSet);

    }

    private void startSkyBgAnim() {

        skyBgImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0f, 1f);
        alphaAnimation.setFillBefore(true);
        alphaAnimation.setFillAfter(true);
        alphaAnimation.setDuration(600);

        animationSet.addAnimation(alphaAnimation);

        skyBgImg.startAnimation(animationSet);
    }

    private void startGameEndAnim() {

        gameEndImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, -MainActivity.dp2pX(64), 0);
        translateAnimation.setFillBefore(false);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(200);

        ScaleAnimation scale1Animation = new ScaleAnimation(1.4f, 1f, 1.4f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale1Animation.setFillBefore(false);
        scale1Animation.setFillAfter(true);
        scale1Animation.setDuration(200);

        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(scale1Animation);

        gameEndImg.startAnimation(animationSet);
    }

    private void startTitleLightAnim() {

        titleLightImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        TranslateAnimation translateAnimation = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -0.5f, Animation.RELATIVE_TO_PARENT, 0,
                Animation.RELATIVE_TO_SELF, 0, Animation.RELATIVE_TO_SELF, 0         );
        translateAnimation.setFillBefore(false);
        translateAnimation.setFillAfter(true);
        translateAnimation.setDuration(250);

        animationSet.addAnimation(translateAnimation);

        titleLightImg.startAnimation(animationSet);
    }


    private void startStarBlingAnim() {
        starBlingImg.setVisibility(VISIBLE);

        AnimationSet animationSet = new AnimationSet(true);

        RotateAnimation rotateAnimation = new RotateAnimation(0, 180,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setFillBefore(false);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(1000);

        ScaleAnimation scale1Animation = new ScaleAnimation(0f, 1f, 0f, 1f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale1Animation.setFillBefore(false);
        scale1Animation.setFillAfter(false);
        scale1Animation.setDuration(500);

        ScaleAnimation scale2Animation = new ScaleAnimation(1f, 0f, 1f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale2Animation.setFillBefore(false);
        scale2Animation.setFillAfter(true);
        scale2Animation.setStartOffset(500);
        scale2Animation.setDuration(500);

        animationSet.addAnimation(rotateAnimation);
        animationSet.addAnimation(scale1Animation);
        animationSet.addAnimation(scale2Animation);

        animationSet.setAnimationListener(generateInvislbleListener(starBlingImg));
        starBlingImg.startAnimation(animationSet);

    }

    private Animation.AnimationListener generateInvislbleListener (final View view) {
        return new Animation.AnimationListener() {
            View targetView = view;
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                targetView.setVisibility(INVISIBLE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        };
    }

}
