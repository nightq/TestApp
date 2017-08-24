package com.wepie.snake.lib.upload.amazon.service;

import com.wepie.snake.lib.upload.amazon.model.AmazonToken;
import com.wepie.snake.lib.upload.amazon.model.AmazonUploadParam;
import com.wepie.snake.lib.upload.amazon.model.AmazonUploadResult;
import com.wepie.snake.lib.upload.base.service.CallBack;
import com.wepie.snake.lib.upload.base.service.UploadService;


/**
 * Created by nightq on 2017/8/23.
 */

public class AmazonUploadService implements UploadService<AmazonUploadParam, AmazonUploadResult> {

    public static final String TAG = "AmazonPlatform";

    @Override
    public void upload(final AmazonUploadParam uploadParam, final CallBack<AmazonUploadResult> callback) {
        // todo nghtq now 使用 amazon 上传 并回调
        AmazonToken amazonToken = uploadParam.getTokenModel();
        if (amazonToken == null) {
            if (callback != null) callback.onFail("failed");
        } else {
            if (callback != null) callback.onSuccess(new AmazonUploadResult("upload result url"));
        }
    }

}
