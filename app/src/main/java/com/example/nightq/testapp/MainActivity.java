package com.example.nightq.testapp;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.BounceInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.image)
    View image;
//    @BindView(R.id.layout2)
//    LinearLayout layout2;
    View layout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);


//        layout2.bringToFront();
    }

    @OnClick(R.id.image)
    public void onViewClicked() {
//        AnimatorSet animatorSet = new AnimatorSet();
////        animatorSet.play()
////                .before()
////                .before(getScaleAnimator(image, 1f, 1f, 0f, 0f));
//        animatorSet.playSequentially(
//                getScaleAnimator(image, 1f, 1f, 2f, 2f),
//                getScaleAnimator(image, 2f, 2f, 1f, 1f),
//                getScaleAnimator(image, 1f, 1f, 2f, 2f),
//                getScaleAnimator(image, 2f, 2f, 1f, 1f)
//        );
//        animatorSet.start();

//        play();
        getReboundAnimator(image).start();
//        getSelfReboundAnimator(image).start();
//        getOpenChestAnimator(image, 100, 300).start();
//        playTest();

    }


    private void playTest() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f);
        valueAnimator.setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                image.setTranslationY((1-progress) *image.getHeight());
            }
        });

        valueAnimator.start();

    }

    public static ValueAnimator getScaleAnimator(final View view,
                                                 final float startScaleX, final float startScaleY,
                                                 final float endScaleX, final float endScaleY) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(1f);
        scaleAnimator.setDuration(500);
//        scaleAnimator.setInterpolator(new OvershootInterpolator());
//        scaleAnimator.setInterpolator(new BounceInterpolator());
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("nightq", "onAnimationUpdate = " + animation.getAnimatedValue());
                float progress = (Float) animation.getAnimatedValue();
                view.setScaleX(startScaleX + progress * (endScaleX - startScaleX));
                view.setScaleY(startScaleY + progress * (endScaleY - startScaleY));

            }
        });
        return scaleAnimator;
    }

    public void play1() {
        long time = 3000;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f);
        valueAnimator.setInterpolator(new BounceInterpolator());
        valueAnimator.setInterpolator(new OvershootInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.e("nightq", "animation = " + animation.getAnimatedValue());
                image.setScaleX((float) animation.getAnimatedValue());
                image.setScaleY((float) animation.getAnimatedValue());
            }
        });
        valueAnimator.setDuration(time);
        valueAnimator.start();
    }

    public void play() {
        long time = 1000;
//        image.setPivotX(0.5f);
//        image.setPivotY(1f);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = calculate((float) animation.getAnimatedValue(), 6);
                progress = 1 + progress / 5;
                image.setScaleX(progress);
                image.setScaleY(100 / (50 + 50 * progress));
                Log.e("nightq", "ValueAnimator = " + animation.getAnimatedValue() + " progress = " + progress);
            }
        });
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setDuration(time);
        valueAnimator.start();
    }


    /**
     * @param percent range {0,1}
     * @param count   >= 1
     * @return
     */
    public float calculate(float percent, int count) {
        return -(float) (Math.log(percent) / Math.log(10) * Math.sin(count * Math.PI * percent));
    }

    /**
     * 果冻动画
     */
    public static ValueAnimator getSelfReboundAnimator(final View view) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(2f);
        scaleAnimator.setDuration(2000);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setPivotX(view.getWidth() / 2);
                view.setPivotY(view.getHeight());

                float progress = (float) animation.getAnimatedValue();
                float scaleX;
                scaleX = 1 + calculateJellyScaleXBySelf(progress, 2);
                view.setScaleX(scaleX);
                view.setScaleY(calculateHFromW(scaleX));
                Log.e("nightq", "p = " + progress + " scaleX = " + scaleX);
            }
        });
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimator.setRepeatMode(ValueAnimator.RESTART);
        return scaleAnimator;
    }

    /**
     * 果冻动画
     */
    public static ValueAnimator getReboundAnimator(final View view) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(2f);
        scaleAnimator.setDuration(2000);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setPivotX(view.getWidth() / 2);
                view.setPivotY(view.getHeight());
                float progress = (float) animation.getAnimatedValue();
                float scaleX;
                if (Float.compare(progress, 0.3f) < 0) {
                    scaleX = 1f + progress * 0.06f / 0.3f;
                } else if (Float.compare(progress, 0.35f) < 0) {
                    scaleX = 1f + ((0.35f - progress) * 0.06f / 0.05f);
                } else {
//                    float
                    scaleX = calculateJellyScaleX(-1.8f, progress - 0.35f);
//                    scaleX = calculateJellyScaleX(-1.2f, progress - 0.35f);
//                    view.setScaleY(1 + calculateJellyScaleXBySelf(((progress-0.35f)/1.65f), 6));
                }
                view.setScaleX(scaleX);
                view.setScaleY(calculateHFromW(scaleX));
                Log.e("nightq", "p = " + progress + " scaleX = " + scaleX);
            }
        });
        scaleAnimator.setRepeatCount(ValueAnimator.INFINITE);
        scaleAnimator.setRepeatMode(ValueAnimator.RESTART);
        return scaleAnimator;
    }


    /**
     * 开启宝箱的时候，宝箱的果冻动画和缩放动画
     */
    public static ValueAnimator getOpenChestAnimator(final View view, final float top, final float bottom) {
        if (view == null) {
            throw new IllegalArgumentException("view is null");
        }
        ValueAnimator scaleAnimator = ValueAnimator.ofFloat(2f);
        scaleAnimator.setDuration(2000);
        scaleAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                view.setPivotX(view.getWidth() / 2);
                view.setPivotY(view.getHeight());
                float progress = (float) animation.getAnimatedValue();
                float scaleX;
                if (Float.compare(progress, 0.2f) < 0) {
                    scaleX = 1f + progress * 0.08f / 0.2f;
                } else if (Float.compare(progress, 0.3f) < 0) {
                    scaleX = 0.9f + (0.3f - progress) * 0.18f / 0.1f;
                } else if (Float.compare(progress, 0.45f) < 0) {
                    scaleX = 0.9f + (progress - 0.3f) * 0.02f / 0.15f;
                } else if (Float.compare(progress, 0.6f) < 0) {
                    scaleX = 0.92f + (progress - 0.45f) * 0.08f / 0.15f;
                } else {
                    scaleX = calculateJellyScaleX(1f, progress - 0.6f);
                }

                float wholeScale = 1;
                if (Float.compare(progress, 0.4f) < 0) {
                    // nothing
                    wholeScale = 1;
                } else if (Float.compare(progress, 0.6f) < 0) {
                    wholeScale = 1f - ((progress - 0.4f) * 0.1f) / 0.2f;
                } else {
                    wholeScale = 0.9f;
                }

                if (Float.compare(progress, 0.3f) < 0) {
                    view.setTranslationY(0);
                } else if (Float.compare(progress, 0.4f) < 0) {
                    view.setTranslationY(-top * (progress - 0.3f) / 0.1f);
                } else if (Float.compare(progress, 0.6f) < 0) {
                    view.setTranslationY(-top + bottom * (progress - 0.4f) / 0.2f);
                } else {
                    view.setTranslationY(-top + bottom);
                }

                view.setScaleX(scaleX * wholeScale);
                view.setScaleY(calculateHFromW(scaleX) * wholeScale);
            }
        });
        return scaleAnimator;
    }

    /**
     * 自己写的填空的果冻动画
     * 函数原型：-log10(x)*sin(6*PI*x)
     *
     * @param percent range {0,1}
     * @param count   >= 1 抖动次数
     * @return
     */
    public static float calculateJellyScaleXBySelf(float percent, int count) {
        return -(float) (Math.log(percent) / Math.log(1000) * Math.sin(count * Math.PI * percent));
    }


    /**
     * 果冻动画，根据 scale x 计算 scale h
     */
    public static float calculateHFromW(float scaleX) {
        return 100 / (50 + 50 * scaleX);
    }

    /**
     * 计算果冻动画的 scale x
     *
     * @param v0 速度
     * @param t  时间
     * @return
     */
    public static float calculateJellyScaleX(float v0, float t) {
        return (float) (1f + (v0 * Math.sin(6 * Math.PI * t) / (6 * Math.PI * Math.pow(Math.E, (2 * t)))));
    }
}
