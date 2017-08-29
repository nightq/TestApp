package com.wepie.snake.lib.upload.data.repository.token.datasource;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;

public interface TokenDataStore {

    <Token extends TokenBase, Param extends UploadParam<Token>> void get(Param param, CallBack<Token> callBack);

}
