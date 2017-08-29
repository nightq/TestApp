package com.wepie.snake.lib.upload.data.domain.model;

/**
 * Created by nightq on 2017/8/15.
 * 上传到 cdn 的参数，一般来说至少包含 path
 */

public class UploadParam<Token extends TokenBase> {

    private String platform;
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

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Token getTokenModel() {
        return token;
    }

    public String getPlatform() {
        return platform;
    }

    public boolean isTokenValid() {
        return token == null;
    }

}
