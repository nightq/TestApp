/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.wepie.snake.lib.upload.data.repository.token.datasource;

import com.wepie.snake.lib.upload.data.cache.token.TokenCache;
import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.net.token.TokenRestApi;

class CloudTokenDataStore implements TokenDataStore {

    private final TokenRestApi restApi;
    private final TokenCache tokenCache;

    public CloudTokenDataStore(TokenRestApi restApi, TokenCache tokenCache) {
        this.restApi = restApi;
        this.tokenCache = tokenCache;
    }

    @Override
    public <Token extends TokenBase, Param extends UploadParam<Token>> void get(Param param, final CallBack<Token> callBack) {
        restApi.get(param, new CallBack<Token>() {
            @Override
            public void onSuccess(Token token) {
                tokenCache.put(token);
                callBack.onSuccess(token);
            }

            @Override
            public void onFail(String msg) {
                callBack.onFail(msg);
            }
        });
    }
}
