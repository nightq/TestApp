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
import com.wepie.snake.lib.upload.data.domain.model.TokenBase;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.net.token.AmazonTokenRestApiImpl;
import com.wepie.snake.lib.upload.data.net.token.QiniuTokenRestApiImpl;
import com.wepie.snake.lib.upload.data.net.token.TokenRestApi;

import static com.wepie.snake.lib.upload.data.config.UploadConfig.PlatformAmazon;
import static com.wepie.snake.lib.upload.data.config.UploadConfig.PlatformQiniu;

public class TokenDataStoreFactory {

    private final TokenCache tokenCache;

    public TokenDataStoreFactory(TokenCache tokenCache) {
        this.tokenCache = tokenCache;
    }

    public <Token extends TokenBase, Param extends UploadParam<Token>> TokenDataStore create(Param param) {
        TokenDataStore userDataStore;

        if (!tokenCache.isValid("param")) {
            userDataStore = new DiskTokenDataStore(tokenCache);
        } else {
            userDataStore = createCloudDataStore(param);
        }

        return userDataStore;
    }

    public <Token extends TokenBase, Param extends UploadParam<Token>> TokenDataStore createCloudDataStore(Param param) {
        final TokenRestApi restApi;
        switch (param.getPlatform()) {
            case PlatformAmazon:
                restApi = new AmazonTokenRestApiImpl();
                break;
            case PlatformQiniu:
            default:
                restApi = new QiniuTokenRestApiImpl();
                break;
        }
        return new CloudTokenDataStore(restApi, tokenCache);
    }
}
