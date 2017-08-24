package com.wepie.snake.lib.upload.qiniu;

import com.wepie.snake.lib.upload.base.UploadTask;
import com.wepie.snake.lib.upload.base.model.UploadParam;
import com.wepie.snake.lib.upload.base.model.UploadResult;
import com.wepie.snake.lib.upload.base.service.UploadService;
import com.wepie.snake.lib.upload.qiniu.model.QiniuToken;
import com.wepie.snake.lib.upload.qiniu.service.QiniuUploadService;

/**
 * Created by nightq
 * 不同上传服务商 创建上传任务的时候 需要不同的参数
 */
public class QiniuTaskBuilder extends UploadTask.Builder<QiniuToken, UploadParam<QiniuToken>, UploadResult> {

    public QiniuTaskBuilder uploadParam(String path) {
        uploadParam(new UploadParam<QiniuToken>(path));
        return this;
    }

    public QiniuTaskBuilder uploadParam(String path, String key) {
        uploadParam(new UploadParam<QiniuToken>(path, key));
        return this;
    }

    @Override
    protected UploadService createUploadService() {
        return new QiniuUploadService();
    }
}
