package com.wepie.snake.lib.upload.qiniu.service;

import android.util.Log;

import com.qiniu.android.storage.UpCompletionHandler;
import com.qiniu.android.storage.UpProgressHandler;
import com.qiniu.android.storage.UploadManager;
import com.qiniu.android.storage.UploadOptions;
import com.wepie.snake.lib.upload.base.model.UploadParam;
import com.wepie.snake.lib.upload.base.model.UploadResult;
import com.wepie.snake.lib.upload.base.service.CallBack;
import com.wepie.snake.lib.upload.base.service.UploadService;
import com.wepie.snake.lib.upload.qiniu.model.QiniuToken;

import org.json.JSONObject;


/**
 * Created by nightq on 2017/8/23.
 */

public class QiniuUploadService implements UploadService<UploadParam<QiniuToken>, UploadResult> {

    public static final String TAG = "QiniuPlatform";

    private static class QiniuHolder {
        static UploadManager uploadManager = new UploadManager();
    }

    private UploadManager getUploadManager () {
        return QiniuHolder.uploadManager;
    }

    @Override
    public void upload(final UploadParam<QiniuToken> uploadParam, final CallBack<UploadResult> callback) {
        UploadOptions uploadOptions = new UploadOptions(null, null, false,
                new UpProgressHandler() {
                    @Override
                    public void progress(String key, double percent) {
                        Log.d(TAG, "a 七牛上传progress:" + percent + "\n" + key);
                    }
                }, null);
        getUploadManager().put(uploadParam.path, uploadParam.key, uploadParam.getTokenModel().token, new UpCompletionHandler() {
            @Override
            public void complete(final String key, final com.qiniu.android.http.ResponseInfo info, final JSONObject resp) {
                Log.i(TAG, "a 七牛上传complete:" + key + ",\r\n " + info + ",\r\n " + resp);
                if (info.isOK()) {
                    String rkey = resp.optString("key", "");
                    final String result = uploadParam.getTokenModel().domain + (uploadParam.getTokenModel().domain.endsWith("/") ? "" : "/") + rkey;
                    if(callback != null) callback.onSuccess(new UploadResult(result));
                } else {
                    Log.e(TAG, "complete: " + info.error);
                    if(callback != null) callback.onFail(info.error);
                }
            }
        }, uploadOptions);
    }



}
