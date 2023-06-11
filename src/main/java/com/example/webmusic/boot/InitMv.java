package com.example.webmusic.boot;

import com.example.webmusic.MusicServerApplication;
import com.example.webmusic.models.mv.Mv;
import com.example.webmusic.service.mv.MvService;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class InitMv {

    public static void main(String[] args) throws IOException {

        //启动springboot
        ApplicationContext context = SpringApplication.run(MusicServerApplication.class, args);
        MvService mvService = context.getBean(MvService.class);
        //设置处理的问价夹目录
        String folderPath = "C:/Users/37359/Desktop/MV";
        String mv_path = "https://web-music-player.oss-cn-guangzhou.aliyuncs.com/mv/";
        //文件对象创建
        File folder = new File(folderPath);
        File[] files = folder.listFiles();
        int i =0;
        //遍历歌曲
        for (File file : files) {
            //如果是歌曲文件
            if (file.isFile() && isSupportedMvFile(file.getName())) {
                String fileName = file.getName();
                String fileType = fileName.substring(fileName.lastIndexOf("."));
                String name = fileName.substring(0,fileName.lastIndexOf("."));
                System.out.println("fileName = " + fileName);
                System.out.println("fileType = " + fileType);
                System.out.println("name = " + name);
                String[] strs = name.split("-");

                String artist = strs[0];
                String movie = strs[1];
                Mv mv = Mv.builder()
                        .artist(artist)
                        .movie(movie)
                        .build();
                mvService.save(mv);
                long id = mv.getMovie_id();
                mv.setUrl(mv_path+mv.getMovie_id());
                mvService.updateById(mv);
                //重命名歌词文件
                // 创建源文件路径对象
                Path sourcePath = Path.of(file.getPath());
                // 创建目标文件夹路径对象
                Path destinationFolderPathObj = Path.of(folderPath);
                // 创建目标文件路径对象
                Path destinationPath = destinationFolderPathObj.resolve(id+".mp4");
                // 复制文件并重命名到目标文件夹
                Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }
    }



    //检测是否是图片文件
    private static boolean isSupportedMvFile(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension.equalsIgnoreCase("mp4");
    }
}
