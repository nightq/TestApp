package com.wepie.snake.lib.upload.data.net.token;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonToken;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonUploadParam;

public class AmazonTokenRestApiImpl implements TokenRestApi<AmazonToken, AmazonUploadParam> {

    public AmazonTokenRestApiImpl() {
    }

    @Override
    public void get(AmazonUploadParam param, CallBack<AmazonToken> callBack) {
        // todo nightq now
        callBack.onSuccess(new AmazonToken(null));
    }
}
