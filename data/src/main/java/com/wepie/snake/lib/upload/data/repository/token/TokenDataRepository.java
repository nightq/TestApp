package com.wepie.snake.lib.upload.data.repository.token;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.repository.TokenRepository;
import com.wepie.snake.lib.upload.data.repository.token.datasource.TokenDataStore;
import com.wepie.snake.lib.upload.data.repository.token.datasource.TokenDataStoreFactory;

/**
 */
public class TokenDataRepository implements TokenRepository {

    private final TokenDataStoreFactory tokenDataStoreFactory;

    public TokenDataRepository(TokenDataStoreFactory tokenDataStoreFactory) {
        this.tokenDataStoreFactory = tokenDataStoreFactory;
    }

    @Override
    public <Token extends TokenBase, Param extends UploadParam<Token>> void getToken(Param param, CallBack<Token> callback) {
        TokenDataStore tokenDataStore = tokenDataStoreFactory.create(param);
        tokenDataStore.get(param, callback);
    }
}
