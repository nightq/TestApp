package com.example.nightq.testapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by nightq on 2018/3/29.
 */

public class FileHelper {


    public static String BASE_FOLDER = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"/testapp/";

    public static boolean copyJarToSD (Context context, String assertDir) {
        AssetManager assetManager = context.getAssets();
        boolean result = true;
        try {
            copyResFromAssetsToFile(assetManager, assertDir, BASE_FOLDER);
        } catch (Throwable e) {
            result = false;
        }
        return result;
    }

    /**
     * 将assets中的动画资源copy到指定目录
     *
     * @param assetManager
     * @param from
     * @param to
     * @throws Exception
     */
    private static void copyResFromAssetsToFile(AssetManager assetManager, String from, String to) throws Exception {
        String[] list = assetManager.list(from);
        for (String path : list) {
            InputStream inputStream = assetManager.open(from + "/" + path);
            copyFile(inputStream, to + from + "/" + path);
            Log.i("999", "------>start process apk from=" + (from + "/" + path) + " to=" + (to + from + "/" + path));
        }
    }


    private static void copyFile(InputStream inputStream, String destFilePath) throws Exception {
        final int BUFFER_SIZE = 1024;
        try {
            FileUtil.createFile(destFilePath);
            FileOutputStream fos = new FileOutputStream(new File(destFilePath));
            byte[] buffer = new byte[BUFFER_SIZE];
            int readByte;
            while ((readByte = inputStream.read(buffer)) != -1) {
                fos.write(buffer, 0, readByte);
            }
            inputStream.close();
            fos.close();

        }catch (Exception e){
            FileUtil.safeDeleteFile(destFilePath);
            throw new Exception("copy failed, " + e.getMessage());
        }
    }

}
