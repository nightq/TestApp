package com.wepie.snake.lib.upload.amazon;

import com.wepie.snake.lib.upload.amazon.model.AmazonToken;
import com.wepie.snake.lib.upload.amazon.model.AmazonUploadParam;
import com.wepie.snake.lib.upload.amazon.model.AmazonUploadResult;
import com.wepie.snake.lib.upload.amazon.service.AmazonUploadService;
import com.wepie.snake.lib.upload.base.UploadTask;
import com.wepie.snake.lib.upload.base.service.UploadService;

/**
 * Created by nightq
 */
public class AmazonTaskBuilder extends UploadTask.Builder<AmazonToken, AmazonUploadParam, AmazonUploadResult> {

    public AmazonTaskBuilder uploadParam(String path) {
        uploadParam(new AmazonUploadParam(path));
        return this;
    }

    @Override
    protected UploadService createUploadService() {
        return new AmazonUploadService();
    }
}
