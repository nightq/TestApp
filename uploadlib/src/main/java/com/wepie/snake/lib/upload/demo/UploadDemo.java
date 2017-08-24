package com.wepie.snake.lib.upload.demo;

import com.wepie.snake.lib.upload.UploadManager;
import com.wepie.snake.lib.upload.amazon.AmazonTaskBuilder;
import com.wepie.snake.lib.upload.qiniu.QiniuTaskBuilder;

/**
 * Created by nightq on 2017/8/24.
 */

public class UploadDemo {

    public static void defaultDemo() {
        // 使用默认或者指定的 上传服务
        UploadManager.builder()
                .uploadParam("path")
                .callback(null)
                .token(null) // or .tokenService(tokenService) 必须二选一
                .upload();
    }

    public static void qiniuDemo() {
        // 指定 上传到七牛的文件名，为空的时候 七牛会自动设定
        String uploadKey = "upload key";
        // 使用默认或者指定的 上传服务
        UploadManager.builder(QiniuTaskBuilder.class)
                .uploadParam("path", uploadKey)
                .callback(null)
                .token(null) // or .tokenService(tokenService) 必须二选一
                .upload();
    }

    public static void amazonDemo() {
        // 指定 amazon 上传
        UploadManager.builder(AmazonTaskBuilder.class)
                .uploadParam("path")
                .callback(null)
                .token(null) // or .tokenService(tokenService) 必须二选一
                .upload();
    }

}
