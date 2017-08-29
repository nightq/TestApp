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
package com.wepie.snake.lib.upload.data.repository.upload.datasource;

import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.net.upload.AmazonUploadRestApiImpl;
import com.wepie.snake.lib.upload.data.net.upload.QiniuUploadRestApiImpl;
import com.wepie.snake.lib.upload.data.net.upload.UploadRestApi;

import static com.wepie.snake.lib.upload.data.config.UploadConfig.PlatformAmazon;
import static com.wepie.snake.lib.upload.data.config.UploadConfig.PlatformQiniu;

public class CDNDataStoreFactory {

    public CDNDataStoreFactory() {
    }

    public CDNDataStore create(UploadParam param) {
        return createCloudDataStore(param);
    }

    private CDNDataStore createCloudDataStore(UploadParam param) {
        final UploadRestApi restApi;
        switch (param.getPlatform()) {
            case PlatformAmazon:
                restApi = new AmazonUploadRestApiImpl();
                break;
            case PlatformQiniu:
            default:
                restApi = new QiniuUploadRestApiImpl();
                break;
        }
        return new CloudCDNDataStore(restApi);
    }
}
