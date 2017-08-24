package com.wepie.snake.lib.upload.base.service;

/**
 * Created by nightq on 2017/8/14.
 */
public interface CallBack<Result> {
    void onSuccess(Result result);
    void onFail(String msg);
}