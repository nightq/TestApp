package com.example.nightq.testapp;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.wepie.scanlib.zxing.utils.Encoder;

/**
 * Created by bigwen on 2016/6/21.
 */
public class ShareUtil {

    public  static void getEncodeBitmap(String encodeUrl, @NonNull Runnable next) {
        new DecodeTask(next, 0xFFFFFF,
                0xFF000000, 138, 138).execute(encodeUrl);
    }

    private static class DecodeTask extends AsyncTask<String, Void, Bitmap> {
        private Runnable next;
        private Encoder mEncoder;

        public DecodeTask(Runnable next) {
            this.next = next;
            mEncoder = new Encoder.Builder()
                    .setBackgroundColor(0xFFFFFF)
                    .setCodeColor(0xFF000000)
                    .setOutputBitmapPadding(0)
                    .setOutputBitmapWidth(138)
                    .setOutputBitmapHeight(138)
                    .build();
        }

        public DecodeTask(Runnable next, int bgColor, int codeColor, int width, int height) {
            this.next = next;
            mEncoder = new Encoder.Builder()
                    .setBackgroundColor(bgColor)
                    .setCodeColor(codeColor)
                    .setOutputBitmapPadding(0)
                    .setOutputBitmapWidth(width)
                    .setOutputBitmapHeight(height)
                    .build();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            return mEncoder.encode(params[0]);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            if (bitmap == null) bitmap = Bitmap.createBitmap(1, 1, Bitmap.Config.RGB_565);
            next.run(bitmap);
        }
    }

    public interface Runnable {
        void run(@NonNull Bitmap bitmap);
    }

}
