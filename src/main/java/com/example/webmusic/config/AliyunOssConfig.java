package com.example.webmusic.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

public class AliyunOssConfig {
    // Endpoint:广州
    static String endpoint = "https://oss-cn-guangzhou.aliyuncs.com";
    // 阿里云账号AccessKey
    static String accessKeyId = "LTAI5tEZPqYCvUwvAcYvUy7W";
    static String accessKeySecret = "pFVyY9pLBITSsCmADv4EMidrjxuNcl";

    // 创建OSSClient实例。
    static public OSS createOssClient(){
        return new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }
}
