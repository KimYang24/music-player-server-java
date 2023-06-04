package com.example.webmusic;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//应用启动类
@SpringBootApplication
//Spring Boot 启动类中添加 @MapperScan 注解，扫描 Mapper 文件夹
@MapperScan("com.example.webmusic.mapper")
public class MusicServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicServerApplication.class, args);
    }
}
