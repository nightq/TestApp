package com.example.nightq.testapp;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import static android.animation.ValueAnimator.INFINITE;

/**
 * Created by nightq 战队赛匹配 的 vs 的动画
 */

public class MatchVSLightView extends View implements ValueAnimator.AnimatorUpdateListener {

    // 当作 轮廓的图
    Bitmap frameBitmap;
    Bitmap maskBitmap;
    private Paint mPaint;

    // 动画进度
    private float progress = 0.5f;

    ValueAnimator valueAnimator;

    public MatchVSLightView(Context context, AttributeSet attrs) {
        super(context, attrs);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
            //View从API Level 11才加入setLayerType方法
            //关闭硬件加速
            setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }
        init();
    }

    public void init() {
        getFrameBitmap();
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        mPaint.setColor(Color.BLUE);
        valueAnimator = ValueAnimator.ofFloat(1f);
        valueAnimator.setDuration(1500);
        valueAnimator.setRepeatCount(INFINITE);
        valueAnimator.addUpdateListener(this);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        valueAnimator.start();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        valueAnimator.cancel();
    }

    private Bitmap getFrameBitmap() {
        if (frameBitmap == null
                || frameBitmap.isRecycled()) {
            try {
                frameBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bg_vs);
            } catch (OutOfMemoryError e) {

            }
        }
        return frameBitmap;
    }

    /**
     * 生成 斜方图
     *
     * @return
     */
    private Bitmap getMaskBitmap() {
        if (maskBitmap == null
                || maskBitmap.isRecycled()) {
            try {
                maskBitmap = makeShapeBmp(
                        42,//ScreenUtil.dip2px(14),
                        90,//ScreenUtil.dip2px(30),
                        Color.parseColor("#7fffffff"),
//                        Color.GREEN,
                        0.3f);
            } catch (OutOfMemoryError e) {

            }
        }
        return maskBitmap;
    }

    /**
     * 生成闪过的 斜方图
     *
     * @return
     */
    private Bitmap makeShapeBmp(int w, int h, int color, float percent) {
        Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);
        Path path = new Path();
        path.moveTo(w * percent, 0);
        path.lineTo(w, 0);
        path.lineTo((1 - percent) * w, h);
        path.lineTo(0, h);
        path.close();
        canvas.drawPath(path, paint);
        return bitmap;
    }

    /**
     * 动画 进度 变化
     *
     * @param animation
     */
    @Override
    public void onAnimationUpdate(ValueAnimator animation) {
        progress = (Float) animation.getAnimatedValue();
        Log.e("nightq", "MatchVSLightView value = " + progress);
        postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.drawColor(Color.BLUE);
//        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));


        Bitmap maskBmp = getMaskBitmap();
        if (maskBmp != null && !maskBmp.isRecycled()) {
            // 滑动的总区域
            int range = (int) (getWidth() * (1.5f) + maskBmp.getWidth());
            // 开始滑动的位置，方块在显示区域左边
            int startPosition = -maskBmp.getWidth();
            int currentposition = startPosition + (int) (progress * range);
            canvas.drawBitmap(maskBmp, currentposition, 0, mPaint);
        }

        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        Bitmap frameBmp = getFrameBitmap();
        if (frameBmp != null && !frameBmp.isRecycled()) {
            canvas.drawBitmap(frameBmp, 0, 0, mPaint);
        }
        mPaint.setXfermode(null);
    }
}
