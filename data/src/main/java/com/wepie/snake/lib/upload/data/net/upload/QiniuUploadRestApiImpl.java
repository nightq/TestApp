package com.wepie.snake.lib.upload.data.net.upload;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;
import com.wepie.snake.lib.upload.data.model.qiniu.QiniuToken;

public class QiniuUploadRestApiImpl implements UploadRestApi<QiniuToken, UploadParam<QiniuToken>, UploadResult> {

    @Override
    public void put(UploadParam<QiniuToken> uploadParam, CallBack<UploadResult> callBack) {

    }
}
