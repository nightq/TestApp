package com.example.nightq.testapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

/**
 * Created by xuejisheng on 2018/5/18.
 */

@SuppressLint("AppCompatCustomView")
public class RatioLayout extends FrameLayout {

    private float ratio = 1f;
    private int originalWidth;
    private int originalHeight;
    private float scale = 1f;

    public RatioLayout(Context context) {
        super(context);
    }

    public RatioLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.RatioLayout);
        originalWidth = a.getInteger(R.styleable.RatioLayout_original_width, 200);
        originalHeight = a.getInteger(R.styleable.RatioLayout_original_height, 100);
        ratio = originalWidth / (float) originalHeight;
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

    }

    public void doScale() {
        setScaleX(scale);
        setScaleY(scale);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        float finalHeight;
        float finalWidth;
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = getMeasuredHeight();
        if (childWidthSize / (float) childHeightSize > ratio) {
            finalWidth = childHeightSize * ratio;
            finalHeight = childHeightSize;
        } else {
            finalWidth = childWidthSize;
            finalHeight = childWidthSize / ratio;
        }

        scale = finalHeight / dip2px(originalHeight);
        Log.e("ssssss", "scale = " + scale);

        widthMeasureSpec = MeasureSpec.makeMeasureSpec(dip2px(originalWidth), MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(dip2px(originalHeight), MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        doScale();
    }

    public int dip2px(int value) {
        float scaledDensity = getResources().getDisplayMetrics().scaledDensity;
        return (int) scaledDensity * value;
    }

}
