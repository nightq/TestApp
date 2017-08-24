package com.wepie.snake.lib.upload.qiniu.model;

import com.wepie.snake.lib.upload.base.model.TokenBase;

/**
 * Created by nightq on 2017/8/15.
 */

public class QiniuToken extends TokenBase {
    public String token;
    public String domain;

    public QiniuToken(String token, String domain) {
        this.token = token;
        this.domain = domain;
    }
}
