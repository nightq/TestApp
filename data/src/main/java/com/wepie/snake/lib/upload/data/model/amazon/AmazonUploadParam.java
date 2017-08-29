package com.wepie.snake.lib.upload.data.model.amazon;


import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.model.amazon.AmazonToken;

/**
 * Created by nightq on 2017/8/15.
 * 上传到 cdn 的参数，一般来说至少包含 path
 */

public class AmazonUploadParam extends UploadParam<AmazonToken> {

    // 上传到服务器的文件夹和文件名
    public String uploadToFolder;

    public AmazonUploadParam(String path) {
        super(path);
    }

}
