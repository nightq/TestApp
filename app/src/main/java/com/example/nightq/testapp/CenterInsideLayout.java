package com.example.nightq.testapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.FrameLayout;


/**
 * Created by xuejisheng on 2018/5/18.
 * 类似 image view 的 centerinside 的布局特点
 */

@SuppressLint("AppCompatCustomView")
public class CenterInsideLayout extends FrameLayout {

    private int originalWidth;
    private int originalHeight;
    /**
     * 记录 scale
     */
    private float scale = 1f;
    private float ratio = 1f;

    public CenterInsideLayout(Context context) {
        super(context);
    }

    public CenterInsideLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.CenterInsideLayout);
        originalWidth = a.getDimensionPixelOffset(R.styleable.CenterInsideLayout_original_width, 0);
        originalHeight = a.getDimensionPixelOffset(R.styleable.CenterInsideLayout_original_height, 0);
        ratio = originalWidth / (float) originalHeight;
    }

    private void doScale() {
        setScaleX(scale);
        setScaleY(scale);
    }

    @SuppressWarnings("unused")
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        scale = 1f;
        if (originalWidth == 0 || originalHeight == 0) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        } else {
            // 设置为原始精确尺寸
            setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
            float finalHeight;
            float finalWidth;
            int childWidthSize = getMeasuredWidth();
            int childHeightSize = getMeasuredHeight();
            // 计算缩放尺寸
            if (childWidthSize / (float) childHeightSize > ratio) {
                finalHeight = childHeightSize;
            } else {
                finalHeight = childWidthSize / ratio;
            }
            scale = finalHeight / originalHeight;
            widthMeasureSpec = MeasureSpec.makeMeasureSpec(originalWidth, MeasureSpec.EXACTLY);
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(originalHeight, MeasureSpec.EXACTLY);
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        doScale();
    }
}
