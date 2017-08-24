
1.获取 token 
---
UploadTokenParamBase ---> UploadTokenService ---> TokenBase

2.使用 token 和上传参数上传到七牛
---
TokenBase + UploadParamBase ---> upload to qiniu