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
    public static int uploadFile(int ID, MultipartFile file,int type) {
        System.out.println("进入uploadFile成功");
        int code = 200;
        String filename = file.getOriginalFilename();
        if (filename == null){
            System.out.println("查找不到文件扩展名！");
            code = 300;
            return code;
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
        OSS client = createOssClient();
        String objectname = prefix + ID + ext;
        PutObjectRequest putObjectRequest = null;
        try {
            putObjectRequest = new PutObjectRequest("web-music-player", objectname, file.getInputStream());
        } catch (IOException e) {
            System.out.println("文件上传失败！");
            code = 300;
            return code;
        }
        PutObjectResult putObjectResult = client.putObject(putObjectRequest);
        System.out.println(putObjectResult.getETag());
        client.shutdown();
        return code;
    }
}
