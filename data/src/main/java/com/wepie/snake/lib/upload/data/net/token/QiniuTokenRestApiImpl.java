package com.wepie.snake.lib.upload.data.net.token;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.model.qiniu.QiniuToken;

public class QiniuTokenRestApiImpl implements TokenRestApi<QiniuToken, UploadParam<QiniuToken>> {


    public QiniuTokenRestApiImpl() {
    }

    @Override
    public void get(UploadParam<QiniuToken> param, CallBack<QiniuToken> callBack) {
        // todo nightq now
        callBack.onSuccess(new QiniuToken(null, null));
    }

}
