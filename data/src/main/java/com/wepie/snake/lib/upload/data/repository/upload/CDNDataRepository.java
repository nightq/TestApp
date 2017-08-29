package com.wepie.snake.lib.upload.data.repository.upload;

import com.wepie.snake.lib.upload.data.domain.callback.CallBack;
import com.wepie.snake.lib.upload.data.domain.model.UploadParam;
import com.wepie.snake.lib.upload.data.domain.model.UploadResult;
import com.wepie.snake.lib.upload.data.domain.repository.CDNRepository;
import com.wepie.snake.lib.upload.data.repository.upload.datasource.CDNDataStore;
import com.wepie.snake.lib.upload.data.repository.upload.datasource.CDNDataStoreFactory;

/**
 */
public class CDNDataRepository implements CDNRepository {

    private final CDNDataStoreFactory uploadDataStoreFactory;

    public CDNDataRepository(CDNDataStoreFactory uploadDataStoreFactory) {
        this.uploadDataStoreFactory = uploadDataStoreFactory;
    }

    @Override
    public <Param extends UploadParam, Result extends UploadResult> void upload(Param param, CallBack<Result> callback) {
        CDNDataStore uploadDataStore = uploadDataStoreFactory.create(param);
        uploadDataStore.put(param, callback);
    }
}
