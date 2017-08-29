package com.wepie.snake.lib.upload.data.repository.upload.datasource;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;

public interface CDNDataStore {

    <Param extends UploadParam, Result extends UploadResult> void put(Param param, CallBack<Result> callBack);

}
