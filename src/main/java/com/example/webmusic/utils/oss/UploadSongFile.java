package com.example.webmusic.utils.oss;

import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.example.webmusic.config.AliyunOssConfig;

import java.io.File;


/**
 *上传文件到oss服务器
 **/


public class UploadSongFile {

    public static void main(String[] args) throws Exception {

        String baseObjectName = "song";
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        // 如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件。
        String filePath= "D:\\localpath\\examplefile.txt";
        String objectName = "sss";
        // 填写Bucket名称
        String bucketName = "web-music-player";
        // 创建OSSClient实例。
        OSS ossClient = AliyunOssConfig.createOssClient();

        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, new File(filePath));
            // 上传文件。
            PutObjectResult result = ossClient.putObject(putObjectRequest);
        } catch (OSSException oe) {
            System.out.println("Error Message:" + oe.getErrorMessage());
        } catch (ClientException ce) {
            System.out.println("Error Message:" + ce.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

}


