package com.wepie.snake.lib.upload.data.domain.interactor;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;

/**
 * Created by nightq on 2017/8/29.
 */

public abstract class UseCase<Param, Result> {

    public UseCase() {
    }

    public abstract void build(Param param, CallBack<Result> callBack);

    public void excute(Param param, CallBack<Result> callBack) {
        build(param, callBack);
    }
}
