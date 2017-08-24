package com.wepie.snake.lib.upload.base.service;

import com.wepie.snake.lib.upload.base.model.UploadParam;
import com.wepie.snake.lib.upload.base.model.UploadResult;

/**
 * Created by nightq
 * 上传服务
 */
public interface UploadService<Param extends UploadParam, Result extends UploadResult> {

    void upload(Param param, CallBack<Result> callback);

}
