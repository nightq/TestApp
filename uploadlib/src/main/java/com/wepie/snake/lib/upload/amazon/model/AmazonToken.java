package com.wepie.snake.lib.upload.amazon.model;

import com.wepie.snake.lib.upload.base.model.TokenBase;

/**
 * Created by nightq on 2017/8/15.
 */

public class AmazonToken extends TokenBase {
    public String secretKey;
    public String secretKey1;
    public String secretKey2;

    public AmazonToken(String secretKey) {
        this.secretKey = secretKey;
    }
}
