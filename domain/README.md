目录结构
===
* base: 上传业务逻辑
* qiniu: 七牛上传包
* amazon: amazon上传包

上传流程
===
* 1.判断 token 是否有效 
* 2.如果无效，get token service 获取 token
* 3.使用 token 上传
* 4.回调结果

结构说明
===
* [Token](../data/src/main/java/com/wepie/snake/lib/upload/data/model/TokenBase.java): 上传到 服务商的 token
* [UploadParam](../data/src/main/java/com/wepie/snake/lib/upload/data/model/UploadParam.java): 上传到 服务器的 参数，包括 本地文件名，上传到服务器的文件名等
* [UploadResult](../data/src/main/java/com/wepie/snake/lib/upload/data/model/UploadResult.java): 回调的返回结果，通常包括 上传结果 url 等
* [GetTokenService](src/main/java/com/wepie/snake/lib/upload/data/domain/interactor/GetTokenService.java): 获取 token 的服务
* [UploadService](src/main/java/com/wepie/snake/lib/upload/data/domain/interactor/UploadService.java): 上传服务
* [CallBack](src/main/java/com/wepie/snake/lib/upload/data/domain/interactor/CallBack.java): 回调的返回结果，通常包括 上传结果 url 等

使用方法
===
在 [UploadManager](./src/main/java/com/wepie/snake/lib/upload/UploadManager.java) 里面配置
见 Demo：

        UploadManager
            .builder() // or .builder(QiniuTaskBuilder.class)
            .uploadParam("path")
            .callback(null)
            .token(null) // or .tokenService(tokenService) 必须二选一
            .upload();