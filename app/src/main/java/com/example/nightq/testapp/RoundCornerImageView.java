package com.example.nightq.testapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by nightq on 2018/4/12.
 */

public class RoundCornerImageView extends ImageView {

    public int leftTopR = 0;
    public int leftBottomR = 0;
    public int rightTopR = 0;
    public int rightBottomR = 0;

    public RoundCornerImageView(Context context) {
        super(context);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RoundCornerImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Path path = new Path();
        int w = getWidth();
        int h = getHeight();

        if (h < leftTopR+leftBottomR || h < rightTopR+rightBottomR
                || w < leftTopR+rightTopR || w < leftBottomR+rightBottomR) {
            super.onDraw(canvas);
            return;
        }

        Point startPoint = new Point();
        if (leftTopR > 0) {
            startPoint = new Point(0, leftTopR);
            path.moveTo(startPoint.x, startPoint.y);
            drawArcPath(path, 0, 0, leftTopR, 180, 90);
        } else {
            startPoint = new Point(0, 0);
            path.moveTo(startPoint.x, startPoint.y);
        }

        if (rightTopR > 0) {
            path.lineTo(w-rightTopR, 0);
            drawArcPath(path, w-2*rightTopR, 0, rightTopR, 270, 90);
        } else {
            path.lineTo(w, 0);
        }

        if (rightBottomR > 0) {
            path.lineTo(w, h-rightBottomR);
            drawArcPath(path, w-2*rightBottomR, h-2*rightBottomR, rightBottomR, 0, 90);
        } else {
            path.lineTo(w, h);
        }

        if (leftBottomR > 0) {
            path.lineTo(leftBottomR, h);
            drawArcPath(path, 0, h-2*leftBottomR, leftBottomR, 90, 90);
        } else {
            path.lineTo(0, h);
        }

        path.lineTo(startPoint.x, startPoint.y);
        path.close();
        canvas.clipPath(path);//将Canvas按照上面的圆角区域截取

        super.onDraw(canvas);
    }

    private void drawArcPath (Path path, int offsetX, int offsetY, int r, int start, int end) {
        RectF rectF = new RectF(offsetX, offsetY, offsetX+2*r, offsetY+2*r);
        path.arcTo(rectF, start, end);
    }

}

