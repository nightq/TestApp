package com.wepie.snake.lib.upload.data.net.upload;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonToken;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonUploadParam;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonUploadResult;

public class AmazonUploadRestApiImpl implements UploadRestApi<AmazonToken, AmazonUploadParam, AmazonUploadResult> {

    @Override
    public void put(AmazonUploadParam uploadParam, CallBack<AmazonUploadResult> callBack) {

    }
}
