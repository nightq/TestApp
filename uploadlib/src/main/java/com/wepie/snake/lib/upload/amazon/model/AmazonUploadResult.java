package com.wepie.snake.lib.upload.amazon.model;

import com.wepie.snake.lib.upload.base.model.UploadResult;

/**
 * Created by nightq on 2017/8/15.
 * 上传到 cdn 的参数，一般来说至少包含 path
 */

public class AmazonUploadResult extends UploadResult {

    // 可能会有很多 其他信息
    public String extroInfo1;
    public String extroInfo2;
    public String extroInfo3;
    public String extroInfo4;

    public AmazonUploadResult(String path) {
        super(path);
    }

}
