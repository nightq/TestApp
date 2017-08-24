package com.wepie.snake.lib.upload;

import com.wepie.snake.lib.upload.amazon.AmazonTaskBuilder;
import com.wepie.snake.lib.upload.base.UploadTask;
import com.wepie.snake.lib.upload.qiniu.QiniuTaskBuilder;

import java.security.InvalidParameterException;

/**
 * Created by nightq on 2017/8/24.
 */

public class UploadManager {

    public static QiniuTaskBuilder builder () {
        return new QiniuTaskBuilder();
    }

    public static <UploadTaskBuilder extends UploadTask.Builder> UploadTaskBuilder builder (Class<UploadTaskBuilder> builderclass) {
        if (builderclass == QiniuTaskBuilder.class) {
            return (UploadTaskBuilder) new QiniuTaskBuilder();
        } else if (builderclass == AmazonTaskBuilder.class) {
            return (UploadTaskBuilder) new AmazonTaskBuilder();
        }
        throw new InvalidParameterException("参数错误");
    }

}
