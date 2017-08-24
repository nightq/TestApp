package com.wepie.snake.lib.upload.base.service;

import com.wepie.snake.lib.upload.base.model.TokenBase;

/**
 * Created by nightq on 2017/8/14.
 */

public interface GetTokenService<Result extends TokenBase> {

    void getToken(CallBack<Result> callback);

}
