package com.wepie.snake.lib.upload.data.domain.interactor;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;
import com.wepie.snake.lib.upload.data.domain.repository.CDNRepository;
import com.wepie.snake.lib.upload.data.domain.repository.TokenRepository;

/**
 * Created by nightq
 * 上传服务
 */
public class UploadFile<Token extends TokenBase, Param extends UploadParam, Result extends UploadResult> extends UseCase<Param, Result> {

    private final CDNRepository cdnRepository;
    private final TokenRepository tokenRepository;

    public UploadFile(CDNRepository cdnRepository, TokenRepository tokenRepository) {
        super();
        this.cdnRepository = cdnRepository;
        this.tokenRepository = tokenRepository;
    }

    @Override
    public void build(final Param param, final CallBack<Result> callBack) {
        final Runnable uploadAction = new Runnable() {
            @Override
            public void run() {
                cdnRepository.upload(param, callBack);
            }
        };
        if (!param.isTokenValid()) {
            tokenRepository.getToken(param, new CallBack<Token>() {
                @Override
                public void onSuccess(Token token) {
                    uploadAction.run();
                }

                @Override
                public void onFail(String msg) {
                    callBack.onFail(msg);
                }
            });
        } else {
            uploadAction.run();
        }
    }
}
