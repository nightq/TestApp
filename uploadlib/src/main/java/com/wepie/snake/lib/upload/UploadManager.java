package com.wepie.snake.lib.upload;

import com.wepie.snake.lib.upload.data.cache.token.TokenCacheImpl;
import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.interactor.UploadFile;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;
import com.wepie.snake.lib.upload.data.domain.repository.CDNRepository;
import com.wepie.snake.lib.upload.data.domain.repository.TokenRepository;
import com.wepie.snake.lib.upload.data.repository.token.TokenDataRepository;
import com.wepie.snake.lib.upload.data.repository.token.datasource.TokenDataStoreFactory;
import com.wepie.snake.lib.upload.data.repository.upload.CDNDataRepository;
import com.wepie.snake.lib.upload.data.repository.upload.datasource.CDNDataStoreFactory;

/**
 * Created by nightq on 2017/8/24.
 */

public class UploadManager {

    private CDNRepository cdnRepository;
    private TokenRepository tokenRepository;

    public UploadManager() {
        cdnRepository = new CDNDataRepository(new CDNDataStoreFactory());
        tokenRepository = new TokenDataRepository(new TokenDataStoreFactory(new TokenCacheImpl()));
    }

    public void upload(String path, String platform, CallBack<UploadResult> callback) {
        UploadFile uploadFile = new UploadFile(cdnRepository, tokenRepository);

        UploadParam uploadParam = new UploadParam(path);
        uploadParam.setPlatform(platform);

        uploadFile.build(uploadParam, callback);
    }

}
