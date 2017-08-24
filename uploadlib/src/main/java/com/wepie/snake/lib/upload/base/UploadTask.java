package com.wepie.snake.lib.upload.base;

import com.wepie.snake.lib.upload.base.model.TokenBase;
import com.wepie.snake.lib.upload.base.model.UploadParam;
import com.wepie.snake.lib.upload.base.model.UploadResult;
import com.wepie.snake.lib.upload.base.service.CallBack;
import com.wepie.snake.lib.upload.base.service.GetTokenService;
import com.wepie.snake.lib.upload.base.service.UploadService;

import java.security.InvalidParameterException;

/**
 * Created by nightq
 */
public class UploadTask<Token extends TokenBase, Param extends UploadParam<Token>, Result extends UploadResult> {

    private Param uploadParam;
    private GetTokenService<Token> tokenService;
    private UploadService<Param, Result> uploadService;
    private CallBack<Result> callBack;

    public void setUploadParam(Param uploadParam) {
        this.uploadParam = uploadParam;
    }

    public void setTokenService(GetTokenService<Token> tokenService) {
        this.tokenService = tokenService;
    }

    public void setUploadService(UploadService<Param, Result> uploadService) {
        this.uploadService = uploadService;
    }

    public void setCallBack(CallBack<Result> callBack) {
        this.callBack = callBack;
    }

    public void upload() {
        getToken(new Runnable() {
            @Override
            public void run() {
                uploadService.upload(uploadParam, callBack);
            }
        });
    }

    private void getToken(final Runnable nextAction) {
        if (uploadParam.getTokenModel() == null) {
            tokenService.getToken(new CallBack<Token>() {
                @Override
                public void onSuccess(Token result) {
                    uploadParam.setToken(result);
                    nextAction.run();
                }

                @Override
                public void onFail(String msg) {
                    if (callBack != null) callBack.onFail("获取 token 失败");
                }
            });
        } else {
            nextAction.run();
        }
    }

    public abstract static class Builder<Token extends TokenBase, Param extends UploadParam<Token>, Result extends UploadResult> {

        private Token token;
        private Param uploadParam;
        private GetTokenService<Token> tokenService;
        private CallBack<Result> callBack;


        /**
         * 如果已经有 token ，直接设置，就不用再调用 get token service 了。
         * @param token
         * @return
         */
        public Builder token(Token token) {
            this.token = token;
            return this;
        }

        protected Builder uploadParam(Param uploadParam) {
            this.uploadParam = uploadParam;
            return this;
        }

        /**
         * 设置获取 token 的方法
         * @param tokenService
         * @return
         */
        public Builder tokenService(GetTokenService<Token> tokenService) {
            this.tokenService = tokenService;
            return this;
        }

        /**
         * 设置回调
         * @param callBack
         * @return
         */
        public Builder callback(CallBack<Result> callBack) {
            this.callBack = callBack;
            return this;
        }

        /**
         * 构建 上传任务
         * @return
         */
        public UploadTask build() {
            UploadTask<Token, Param, Result> uploadTask = new UploadTask<>();
            if (token == null && tokenService == null) {
                throw new InvalidParameterException("不能正确获取 token");
            }
            if (uploadParam == null) {
                throw new InvalidParameterException("uploadParam is null");
            }
            if (token != null) {
                uploadParam.setToken(token);
            }
            uploadTask.setUploadParam(uploadParam);
            uploadTask.setUploadService(createUploadService());
            uploadTask.setTokenService(tokenService);
            uploadTask.setCallBack(callBack);
            return uploadTask;
        }

        /**
         * 执行上传人物
         */
        public void upload() {
            UploadTask task = build();
            task.upload();
        }

        /**
         * 获取上传服务
         * @return
         */
        protected abstract UploadService<Param, Result> createUploadService();
    }

}
