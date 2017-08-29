package com.wepie.snake.lib.upload.data.domain.repository;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;

/**
 * Created by nightq
 * 上传服务
 */
public interface CDNRepository {

    <Param extends UploadParam, Result extends UploadResult> void upload(Param param, CallBack<Result> callback);

}
