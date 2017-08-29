package com.wepie.snake.lib.upload.data.model.amazon;

import com.wepie.snake.lib.upload.data.domain.model.TokenBase;

/**
 * Created by nightq on 2017/8/15.
 */

public class AmazonToken extends TokenBase {
    public String secretKey;

    public AmazonToken(String secretKey) {
        this.secretKey = secretKey;
    }
}
