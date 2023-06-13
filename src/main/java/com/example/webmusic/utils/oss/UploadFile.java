package com.example.webmusic.utils.oss;

import java.io.*;
import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectResult;
import com.example.webmusic.config.AliyunOssConfig;
import org.springframework.web.multipart.MultipartFile;
import java.util.Objects;

import static com.example.webmusic.config.AliyunOssConfig.createOssClient;

public class UploadFile {
    public static void uploadFile(int ID, MultipartFile file,int type,OutApi_uploadFile out) throws IOException {
        String filename = file.getOriginalFilename();
        System.out.println("获取文件名成功："+filename);
        if (filename == null){
            System.out.println("查找不到文件扩展名！");
            out.setCode(300);
            return;
        }
        String ext = filename.substring(filename.lastIndexOf("."));
        String prefix = "";
        switch (type) {
            case 1:
                prefix = "image/userpic/";
                break;
            case 2:
                break;
        }
        System.out.println("决定文件存储路径成功！");
        OSS client = AliyunOssConfig.createOssClient();
        System.out.println("创建client成功！");
        String objectname = prefix + ID + ext;
        System.out.println("存储文件名："+ objectname);
        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest("web-music-player", objectname, file.getInputStream());
        } catch (IOException e) {
            System.out.println("文件上传失败！");
            out.setCode(300);
            return;
        }
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        System.out.println(putObjectResult.getETag());
        client.shutdown();
        out.setPicUrl("https://web-music-player.oss-cn-guangzhou.aliyuncs.com/" + objectname);
        System.out.println(out.getPicUrl());
        out.setCode(200);
    }
}
