package com.wepie.snake.lib.upload.base.model;

/**
 * Created by nightq on 2017/8/15.
 * 上传到 cdn 的参数，一般来说至少包含 path
 */

public class UploadParam<Token extends TokenBase> {
    public String path;
    public String key;
    private Token token;

    public UploadParam(String path) {
        this.path = path;
    }

    public UploadParam(String path, String key) {
        this(path);
        this.key = key;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getTokenModel() {
        return token;
    }
}
