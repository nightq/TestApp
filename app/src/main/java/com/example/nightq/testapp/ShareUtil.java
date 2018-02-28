package com.example.nightq.testapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by bigwen on 2016/6/21.
 */
public class ShareUtil {

    public static Bitmap makeLifeShareBitmap(Context context) {
        View shareView = LayoutInflater.from(context).inflate(R.layout.activity_main2, null, false);

        TextView nicknameIv = (TextView) shareView.findViewById(R.id.nickname);
        TextView scoreIv = (TextView) shareView.findViewById(R.id.score);
        TextView killIv = (TextView) shareView.findViewById(R.id.kill);
        TextView comboMaxIv = (TextView) shareView.findViewById(R.id.combo_max);
        TextView goldCoinIv = (TextView) shareView.findViewById(R.id.gold_coin);


        int widthSpec = View.MeasureSpec.makeMeasureSpec(1334, View.MeasureSpec.EXACTLY);
        int heightSpec = View.MeasureSpec.makeMeasureSpec(750, View.MeasureSpec.EXACTLY);
        shareView.measure(widthSpec, heightSpec);
        shareView.layout(0, 0, 1334, 750);

        Bitmap bmp = Bitmap.createBitmap(1334, 750, Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bmp);
        shareView.draw(canvas);

        return bmp;
//        return view2Bitmap(shareView, 667, 375);
    }

    /**
     * view转Bitmap
     *
     * @param view 视图
     * @return bitmap
     */
    public static Bitmap view2Bitmap(final View view, int width, int height) {
        Bitmap bitmap = null;
        if (view != null) {
            view.clearFocus();
            view.setPressed(false);

            boolean willNotCache = view.willNotCacheDrawing();
            view.setWillNotCacheDrawing(false);

            int color = view.getDrawingCacheBackgroundColor();
            view.setDrawingCacheBackgroundColor(0);
            float alpha = view.getAlpha();
            view.setAlpha(1.0f);

            if (color != 0) {
                view.destroyDrawingCache();
            }

            int widthSpec = View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY);
            view.measure(widthSpec, heightSpec);
            view.layout(0, 0, width, height);

            view.buildDrawingCache();
            Bitmap cacheBitmap = view.getDrawingCache();
            if (cacheBitmap == null) {
                Log.e("view.ProcessImageToBlur", "failed getViewBitmap(" + view + ")",
                        new RuntimeException());
                return null;
            }
            bitmap = Bitmap.createBitmap(cacheBitmap);
            // Restore the view
            view.setAlpha(alpha);
            view.destroyDrawingCache();
            view.setWillNotCacheDrawing(willNotCache);
            view.setDrawingCacheBackgroundColor(color);
        }
        return bitmap;
    }


    private interface Runnable {
        void run(@NonNull Bitmap bitmap);
    }

    public static class ShareBuild {

        private ShareParam shareParam;

        class ShareParam {
            private int score;
            private int killNum;
            private int comboMax;
            private int goldCoin;
            private String rank;
            private int rankType;
            private int rankNumber;
            private Bitmap bitmap;
            private int buildType;
            private boolean isMvp;
            private String scorePercentage;
            private int robCoinRankIndex;
            private float robCoinTotalCoin;
            private int robCoinIncome;
            private String encodeUrl;
        }

        public ShareBuild() {
            shareParam = new ShareParam();
        }

        /**
         * 必须
         *
         * @param score 长度or分数
         * @return
         */
        public ShareBuild setScore(int score) {
            shareParam.score = score;
            return this;
        }

        /**
         * 挑战模式击杀数目
         */
        public ShareBuild setKillNum(int killNum) {
            shareParam.killNum = killNum;
            return this;
        }

        /**
         * 挑战模式最大连击数
         */
        public ShareBuild setComboMax(int comboMax) {
            shareParam.comboMax = comboMax;
            return this;
        }

        /**
         * 挑战模式本局获得金豆数
         */
        public ShareBuild setGoldCoin(int goldCoin) {
            shareParam.goldCoin = goldCoin;
            return this;
        }

        /**
         * buildType = SHARE_TPYE_SOCRE 需要
         *
         * @param rank 排名百分比
         * @return
         */
        public ShareBuild setRank(String rank) {
            shareParam.rank = rank;
            return this;
        }

        /**
         * buildType = SHARE_TYPE_RANK 需要
         *
         * @param rankType 游戏类型
         * @return
         */
        public ShareBuild setRankType(int rankType) {
            shareParam.rankType = rankType;
            return this;
        }

        /**
         * @param rankNumber 排名
         * @return
         */
        public ShareBuild setRankNumber(int rankNumber) {
            shareParam.rankNumber = rankNumber;
            return this;
        }

        /**
         * 必须
         *
         * @param buildType 构建类型
         * @return
         */
        public ShareBuild setBuildType(int buildType) {
            shareParam.buildType = buildType;
            return this;
        }

        /**
         * 必须
         *
         * @param bitmap 分享的二维码
         * @return
         */
        public ShareBuild setBitmap(Bitmap bitmap) {
            shareParam.bitmap = bitmap;
            return this;
        }

        public ShareBuild setIsMvp(boolean isMvp) {
            shareParam.isMvp = isMvp;
            return this;
        }

        public ShareBuild setPercent(String percent) {
            shareParam.scorePercentage = percent;
            return this;
        }

        public ShareBuild setRobCoinRankIndex(int rankIndex) {
            shareParam.robCoinRankIndex = rankIndex;
            return this;
        }

        public ShareBuild setRobCoinTotalCoin(float totalCoin) {
            shareParam.robCoinTotalCoin = totalCoin;
            return this;
        }

        public ShareBuild setRobCoinIncome(int income) {
            shareParam.robCoinIncome = income;
            return this;
        }

        public ShareBuild setEncodeUrl(String encodeUrl) {
            shareParam.encodeUrl = encodeUrl;
            return this;
        }

        public ShareParam build() {
            return shareParam;
        }
    }
}
