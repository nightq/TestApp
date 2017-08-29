package com.wepie.snake.lib.upload.data.domain.repository;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;

/**
 * Created by nightq on 2017/8/14.
 */

public interface TokenRepository {

    <Token extends TokenBase, Param extends UploadParam<Token>> void getToken(Param param, CallBack<Token> callback);

}
