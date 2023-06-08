package com.example.webmusic.config;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class AliyunOssConfig {
    // Endpoint:广州
    // 阿里云账号AccessKey
    // 创建OSSClient实例。
    static public OSS createOssClient() throws IOException {
        File file = new File("./key.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String,Object> map = objectMapper.readValue(file,new TypeReference<Map<String,Object>>(){});
        return new OSSClientBuilder().build(
                (String) map.get("endpoint"),
                (String) map.get("accessKeyId"),
                (String) map.get("accessKeySecret"));
    }

}
